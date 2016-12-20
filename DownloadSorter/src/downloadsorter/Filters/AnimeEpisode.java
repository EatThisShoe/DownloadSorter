package downloadsorter.Filters;


import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Eric
 */
public class AnimeEpisode implements FileMetaData {
    private final Path path;
    private final String name;
    private Boolean hasFansubFormat;
    
    public AnimeEpisode(FileMetaData f) {
        path = f.getPath();
        hasFansubFormat = false;
        name = extractSeriesName();
    }
    
    public final String extractSeriesName() {
        String s = getPath().toString();
        Pattern p = Pattern.compile("\\[.*\\] (.*) -.*");
        Matcher m = p.matcher(s);
        Boolean b = m.find();
        
        if (b) {
            hasFansubFormat = true;
            return m.group(1).trim();
        }
        else
            return s;
    }
    
    @Override
    public String toString() {
        return getName();
    }

    /**
     * @return the location
     */
    @Override
    public Path getPath() {
        return path;
    }

    /**
     * @return the seriesName
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the hasFansubFormat
     */
    @Override
    public boolean isMatch() {
        return hasFansubFormat;
    }
}
