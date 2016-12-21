/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.Filters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Eric
 */
public class FansubFilter implements FilterRule {

    @Override
    public List<FileMetaData> filterFiles(List<FileMetaData> fansubs) {
        return fansubs.stream()
                    .map(f -> setSeriesName(f))
                    .filter(f -> !f.getAttribute("series name").equals(""))
                    .filter(f -> findSameSeries(fansubs, f))
                    .filter(f -> !Files.isDirectory(f.getPath())) //should remove directories after counting them for duplicates
                    .collect(Collectors.toList());
    }
    
    private Boolean findSameSeries(List<FileMetaData> allFiles, FileMetaData aFile) {
        Boolean hasSeriesMatch = false;
        for(FileMetaData file : allFiles) {
            String series1 = file.getAttribute("series name");
            String series2 = aFile.getAttribute("series name");
            if(series1 != null && series2 != null && series1.equals(series2))
                    hasSeriesMatch = true;
        }
        return hasSeriesMatch;
        //return allFiles.stream().anyMatch(a -> a.getAttribute("series name").equals(aFile.getAttribute("series name")) && a.getPath() != aFile.getPath());
    }
    
    private FileMetaData setSeriesName(FileMetaData file) {
        String s = file.getPath().toString();
        Pattern p = Pattern.compile("\\[.*\\] (.*) -.*");
        Matcher m = p.matcher(s);
        Boolean b = m.find();
        
        if(b)
            file.addAttribute("series name", m.group(1).trim());
        else
            file.addAttribute("series name", "");
        return file;
    }
    
    @Override
    public String toString() {
        return ("FansubFilter,");
    }
}
