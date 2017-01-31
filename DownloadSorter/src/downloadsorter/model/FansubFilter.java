/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

import downloadsorter.view.rulepanes.UIField;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Eric
 */
public class FansubFilter implements FilterRule, Rule {
    static final String fxmlPath = "view/rulepanes/FansubFilter.fxml";

    @Override
    public List<FileMetaData> filterFiles(List<FileMetaData> fansubs) {
        return fansubs.stream()
                    .map(f -> setSeriesName(f))
                    .filter(f -> f.getAttribute(FileAttributes.seriesName) != null)
                    .filter(f -> findSameSeries(fansubs, f))
                    .filter(f -> !Files.isDirectory(f.getPath())) //should remove directories after counting them for duplicates
                    .collect(Collectors.toList());
    }
    
    private Boolean findSameSeries(List<FileMetaData> allFiles, FileMetaData aFile) {
        Boolean hasSeriesMatch = false;
        for(FileMetaData file : allFiles) {
            String series1 = file.getAttribute(FileAttributes.seriesName);
            String series2 = aFile.getAttribute(FileAttributes.seriesName);
            if(series1 != null && series2 != null && series1.equals(series2))
                hasSeriesMatch = true;
        }
        return hasSeriesMatch;
    }
    
    private FileMetaData setSeriesName(FileMetaData file) {
        String s = file.getPath().toString();
        Pattern p = Pattern.compile("\\[.*\\] (.*) -.*");
        Matcher m = p.matcher(s);
        Boolean b = m.find();
        
        if(b)
            file.addAttribute(FileAttributes.seriesName, m.group(1).trim());
//        else
//            file.addAttribute("series name", "");
        return file;
    }
    @Override
    public String getDescription() {
        return "Include anime Fansubs";
    }
    
    @Override
    public String toString() {
        return (FansubFilter.class.getCanonicalName() + ",");
    }
    

    @Override
    public String getFXMLPath() {
        return fxmlPath;
    }

    @Override
    public UIField[] getFields() {
        UIField[] fields = new UIField[0];
        return fields;
    }
}
