
/*Han Huang
 * huang.han3@northeastern.edu
 * 5008 homework7
 * Han Tables
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TABLE_SIZE 50

typedef struct {
    char *word;
    int frequency;
} HashItem;

HashItem* hashTable[TABLE_SIZE] = {NULL};

unsigned int hash(const char *word) {
    unsigned long int value = 0;
    unsigned int i = 0;
    unsigned int word_len = strlen(word);

    for(; i < word_len; ++i) {
        value = value * 37 + word[i];
    }

    value = value % TABLE_SIZE;
    return value;
}

void insert(const char *word) {
    unsigned int index = hash(word);

    while (hashTable[index] != NULL && strncmp(hashTable[index]->word, word, TABLE_SIZE) != 0) {
        index = (index + 1) % TABLE_SIZE;
    }

    if (hashTable[index] != NULL) {
        hashTable[index]->frequency += 1;
    } else {
        HashItem *item = (HashItem*)malloc(sizeof(HashItem));
        item->word = (char*)malloc(strlen(word) + 1);
        strcpy(item->word, word);
        item->frequency = 1;
        hashTable[index] = item;
    }
}

int cmp(const void *a, const void *b) {
    HashItem *item1 = *(HashItem**)a;
    HashItem *item2 = *(HashItem**)b;
    return item2->frequency - item1->frequency; // Descending order
}

void printFrequencies() {
    printf("Word Frequencies:\n");

    HashItem *sortedItems[TABLE_SIZE];
    int sortedIndex = 0;

    for (int i = 0; i < TABLE_SIZE; ++i) {
        if (hashTable[i] != NULL) {
            sortedItems[sortedIndex++] = hashTable[i];
        }
    }

    qsort(sortedItems, sortedIndex, sizeof(HashItem*), cmp);

    for (int i = 0; i < sortedIndex; ++i) {
        printf("%s: %d\n", sortedItems[i]->word, sortedItems[i]->frequency);
    }
}

int main() {
    char *words[] = {"apple", "banana", "apple", "orange", "banana", "apple"};
    int wordsCount = 6;

    for (int i = 0; i < wordsCount; ++i) {
        insert(words[i]);
    }

    printFrequencies();

    for (int i = 0; i < TABLE_SIZE; ++i) {
        if (hashTable[i] != NULL) {
            free(hashTable[i]->word);
            free(hashTable[i]);
        }
    }

    return 0;
}


