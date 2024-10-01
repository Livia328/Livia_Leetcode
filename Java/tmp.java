import java.rmi.Remote;
import java.util.*;

public class tmp {
    public static String[] createFrame(int n) {
        // Create an array of strings to hold the frame
        String[] frame = new String[n];
        
        // Fill the frame
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                // Check if we are on the border
                if (i == 0 || i == n - 1 || j == 0 || j == n - 1) {
                    row.append('*');  // Add a star at the border
                } else {
                    row.append(' ');  // Add a space inside the frame
                }
            }
            frame[i] = row.toString(); // Convert StringBuilder to String and store it in the array
        }
        
        return frame;
    }
}
