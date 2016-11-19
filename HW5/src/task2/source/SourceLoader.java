package task2.source;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SourceLoader should contain all implementations of SourceProviders to be able to load different sources.
 */
public class SourceLoader {

    private List<SourceProvider> sourceProviders = new ArrayList<>();

    public SourceLoader() {
        sourceProviders.add(new URLSourceProvider());
        sourceProviders.add(new FileSourceProvider());
    }

    public String loadSource(String pathToSource) throws SourceLoadingException {
        Matcher featureLink = Pattern.compile("^(ht|f)tp(s?)://[-a-z0-9._/]+$").matcher(pathToSource);
        Matcher featureFilePath = Pattern.compile("^([a-zA-Z]:)?(\\\\[^<>:\"/\\\\|?*]+)+\\\\?$").matcher(pathToSource);

        if (featureLink.find()) {
            if (sourceProviders.get(0).isAllowed(pathToSource)) {
                return sourceProviders.get(0).load(pathToSource);
            }
        } else if (featureFilePath.find()) {
            if (sourceProviders.get(1).isAllowed(pathToSource)) {
                return sourceProviders.get(1).load(pathToSource);
            }
        }
        return null;
    }
}