package Stripe;

import java.util.*;

public class HttpHeader {
    /*
     * 因为我们要快速keep order
     * 所以LinkedHashSet
     * 
     * followup是如果要可以remove的话，可以用double linkedlist
     */
    private static final String SPLITTER = ", ";
    public static List<String> parseAcceptLanguage(String headers, List<String> supportedLanguagesForSever) {
        List<String> res = new ArrayList<>();
        if (headers == null || headers.isEmpty() || supportedLanguagesForSever == null || supportedLanguagesForSever.isEmpty())  {
            return res;
        }
        LinkedHashSet<String> resSet = new LinkedHashSet<>();
        // key是prefix，val是所有的语言
        Map<String, Set<String>> tagMap = new HashMap<>();
        for (String curLang: supportedLanguagesForSever) {
            String curTag = curLang.substring(0, 2);
            tagMap.putIfAbsent(curTag, new LinkedHashSet<>());
            tagMap.get(curTag).add(curLang);
        }
        String[] supportedLanguagesForClient = headers.split(SPLITTER);
        for (String curLang: supportedLanguagesForClient) {
            // 先匹配exact match
            if (supportedLanguagesForSever.contains(curLang)) {
                // 如果这个已经加过了
                // 也就是[Fr, Fr-CA]的情况
                // 我们要把这个放到最后
                if (resSet.contains(curLang)) {
                    resSet.remove(curLang);
                    resSet.add(curLang);
                } else {
                    // 如果是第一次遇到，就直接加入
                    resSet.add(curLang);
                }
                // 遇到Fr的情况
                // 把这个prefix对应的全部加入
            } else if (tagMap.containsKey(curLang)){
                for (String s: tagMap.get(curLang)) {
                    // 因为是linkedlist hashset
                    // 所以可以保留原来的order
                    resSet.add(s);
                }
            } else if ("*".equals(curLang)) {
                // 把所有的加进去
                for (String key : tagMap.keySet()) {
                    resSet.addAll(tagMap.get(key));
                }
            }
        }
        res.addAll(resSet);
        return res;
    }

    public static void main(String[] args) {
        // ["en-US", "fr-FR"]
        List<String> supported1 = Arrays.asList("fr-FR", "en-US");
        System.out.println(parseAcceptLanguage("en-US, fr-CA, fr-FR", supported1));

        // ["fr-FR"]
        List<String> supported2 = Arrays.asList("en-US", "fr-FR");
        System.out.println(parseAcceptLanguage("fr-CA, fr-FR", supported2));

        // ["en-US"]
        List<String> supported3 = Arrays.asList("en-US", "fr-CA");
        System.out.println(parseAcceptLanguage("en-US", supported3));

        // ["en-US"]
        List<String> supported4 = Arrays.asList("en-US", "fr-CA", "fr-FR");
        System.out.println(parseAcceptLanguage("en", supported4));

        // ["fr-CA", "fr-FR"]
        List<String> supported5 = Arrays.asList("en-US", "fr-CA", "fr-FR");
        System.out.println(parseAcceptLanguage("fr", supported5));

        // ["fr-FR", "fr-CA"]
        List<String> supported6 = Arrays.asList("en-US", "fr-CA", "fr-FR");
        System.out.println(parseAcceptLanguage("fr-FR, fr", supported6));

        // 如果先加了fr全部，那么要把exact match的放到最后
        // ["fr-FR", "fr-UR", "fr-CD",fr-CA]
        List<String> supported7 = Arrays.asList("en-US", "fr-CA", "fr-FR", "fr-UR", "fr-CD");
        System.out.println(parseAcceptLanguage("fr, fr-CA", supported7));
    }
}
