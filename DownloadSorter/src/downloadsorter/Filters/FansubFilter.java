/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Filters;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Eric
 */
public class FansubFilter implements FilterRule {

    @Override
    public List<FileMetaData> filterFiles(List<Path> l) {
        List<FileMetaData> fansubs = l.stream()
                    .map(AnimeEpisode::new)
                    .collect(Collectors.toList());
        
        return fansubs.stream()
                    .filter(f -> fansubs.stream().anyMatch(a -> a.getName().equals(f.getName()) && a.getPath() != f.getPath()))
                    .filter(f -> f.isMatch()) //should remove directories after counting them for duplicates
                    .collect(Collectors.toList());
    }
}
