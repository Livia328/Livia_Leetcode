#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SIZE 100

// Global stack and top variable declaration
char stack[MAX_SIZE];
int top = -1;

// Function to push a character onto the stack
void push(char data) {
    // write your code here
}

// Function to pop a character from the stack
char pop() {
    // write your code here
}

// Function to reverse a string using a stack
void reverse_string(char *str) {
    // write your code here
}

// Main function
int main() {
    char text[MAX_SIZE];
    printf("Input a string: ");
    fgets(text, MAX_SIZE, stdin);

    // Remove newline character from input
    text[strcspn(text, "\n")] = '\0';

    // Reverse the input string using the stack
    reverse_string(text);

    // Print the reversed string
    printf("Reversed string using a stack is: %s\n", text);

    return 0;
}
