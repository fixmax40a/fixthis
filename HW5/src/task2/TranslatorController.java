package task2;

import task2.source.SourceLoader;
import task2.source.SourceLoadingException;
import task2.source.URLSourceProvider;

import java.util.Scanner;

public class TranslatorController {

    public static void main(String[] args) {

        SourceLoader sourceLoader = new SourceLoader();
        Translator translator = new Translator(new URLSourceProvider());

        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        while (!"exit".equals(command)) {
            //TODO: add exception handling here to let user know about it and ask him to enter another path to translation
            //So, the only way to stop the application is to do that manually or type "exit"

            //command = "https://gist.githubusercontent.com/pistriak/3c42490bbe2de0dd7934a98d3f09514d/raw/9c2f2155ec3c5b47325bd974f2f3b7567f478afd/example.txt";

            try {
                String source;
                try {
                    source = sourceLoader.loadSource(command);
                } catch (SourceLoadingException e) {
                    throw new TranslateException(e);
                }
                String translation = translator.translate(source);

                System.out.println("Original: " + source);
                System.out.println("Translation: " + translation);
            } catch (TranslateException e) {
                System.err.println(e.getMessage());
            }
            command = scanner.next();

        }
    }
}