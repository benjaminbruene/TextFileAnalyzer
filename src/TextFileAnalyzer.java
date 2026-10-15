import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFileAnalyzer {
    public static void main(String[] args){
        //Declare a Scanner
        Scanner scan = new Scanner(System.in);

        //Define the files
        String txtFileName = "time.txt";
        String docxFileName = "time.docx";

        //Get the Char to count from the user
        System.out.println("Welcome to the Text File Analyzer");

        System.out.print("Enter the character you want to count: ");
        String userInput = scan.nextLine();

        //Filter malicious input
        while (userInput.isEmpty()){
            System.out.print("Enter at least one character: ");
            userInput = scan.nextLine();
        }

        char searchChar = userInput.charAt(0);

        //Open the files
        File txtFile = new File(txtFileName);
        File docxFile = new File(docxFileName);

        //Analyze the files
        try {
            //Check the text file
            int lineCount = countLines(txtFile);
            int wordCount = countWords(txtFile);
            int characterCount = countCharacter(txtFile, searchChar);

            System.out.println();
            System.out.println("Text File Analysis: ");
            System.out.println("File Name: " + txtFile.getName());
            System.out.println("File Size: " + txtFile.length() + " bytes");
            System.out.println("Line Count: " + lineCount);
            System.out.println("Word Count: " + wordCount);
            System.out.println("Character count for: " + searchChar + " : " + characterCount);
            System.out.println();

            //Check the docx files:
            System.out.println("Docx File Analysis: ");
            System.out.println("File Name: " + docxFile.getName());
            System.out.println("File Size: " + docxFile.length() + " bytes");
            System.out.println();

            //Comparison
            System.out.println("Comparison: ");
            if (docxFile.length() > txtFile.length())
                System.out.println("The DOCX file is larger than the TXT file.");
            else if (docxFile.length() < txtFile.length())
                System.out.println("The TXT file is larger than the DOCX file.");
            else {
                System.out.println("The file sizes are the same.");
            }

        } catch (FileNotFoundException e){
            System.out.println("File Error: " + e.getMessage());
            System.out.println("Make sure the text and docx files are in the project folder.");
        }

        scan.close();
    }
    //Helper Methods
    //Method to count Lines
    public static int countLines(File file) throws FileNotFoundException {
        Scanner fileReader = new Scanner(file);
        int lineCount = 0;

        while(fileReader.hasNextLine()){
            fileReader.nextLine();
            lineCount++;
        }

        fileReader.close();
        return lineCount;
    }

    //Method to count Words
    public static int countWords(File file) throws FileNotFoundException {
        Scanner fileReader = new Scanner(file);
        int wordCount = 0;

        while(fileReader.hasNext()) {
            fileReader.next();
            wordCount++;
        }

        fileReader.close();
        return wordCount;
    }

    //Method to count Chars
    public static int countCharacter(File file, char searchCharacter) throws FileNotFoundException {
        Scanner fileReader = new Scanner(file);
        int characterCount = 0;
        char lowerChar = Character.toLowerCase(searchCharacter);

        while (fileReader.hasNextLine()){
            String line = fileReader.nextLine();

            for(int i = 0; i < line.length(); i++){
                char currentChar = Character.toLowerCase(line.charAt(i));

                if(currentChar == lowerChar)
                    characterCount++;
            }
        }

        fileReader.close();
        return characterCount;
    }
}
