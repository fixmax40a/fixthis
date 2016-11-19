package task2.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Implementation for loading content from specified URL.<br/>
 * Valid paths to load are http://someurl.com, https://secureurl.com, ftp://frpurl.com etc.
 */
public class URLSourceProvider implements SourceProvider {

    @Override
    public boolean isAllowed(String pathToSource) {

        URLConnection connection = null;
        try {
            connection = new URL(pathToSource).openConnection();
        } catch (IOException e) {
            System.err.println("Ресурс не доступен!!!");
        }
        return connection != null;
    }

    @Override
    public String load(String pathToSource) throws SourceLoadingException {
        StringBuilder UrlLoadedResult = new StringBuilder();

        try {
            URL url = new URL(pathToSource);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String input;
                while ((input = reader.readLine()) != null) {
                    UrlLoadedResult.append(input);
                }
            }
        } catch (IOException e) {
            throw new SourceLoadingException("Ошибка чтения ресурса!!!", e);
        }

        return UrlLoadedResult.toString();
    }
}