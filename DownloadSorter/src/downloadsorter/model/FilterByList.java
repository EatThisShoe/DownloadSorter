/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter.model;

import downloadsorter.view.rulepanes.FileField;
import downloadsorter.view.rulepanes.FlagField;
import downloadsorter.view.rulepanes.UIField;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Eric
 */
public class FilterByList implements FilterRule, Rule{
    final static String fxmlPath ="view/rulepanes/FilterByList.fxml";
    private Boolean include;
    private Boolean useAbsolutePaths;
    private Path listLocation;
    
    public FilterByList(Path listLocation, Boolean include, Boolean absolute) {
         this.listLocation = listLocation;
         this.include = include;
         useAbsolutePaths = absolute;
    }
    
    public FilterByList(String[] fromFile) {
        include = Boolean.parseBoolean(fromFile[1]);
        useAbsolutePaths = Boolean.parseBoolean(fromFile[2]);
        listLocation = Paths.get(fromFile[3]);
    }
    
    public FilterByList() {
        listLocation = Paths.get("");
        include = false;
        useAbsolutePaths = false;
    }

    @Override
    public List<FileMetaData> filterFiles(List<FileMetaData> inputList) {
        List<FileMetaData> outputList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(getListLocation());
            List<Path> filesInList = new ArrayList<>();
            lines.forEach(line -> filesInList.add(Paths.get(line)));
            
            outputList = inputList.stream()
                .filter(file -> test(file, filesInList))
                .collect(Collectors.toList());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return outputList;
    }
    
    private Boolean test(FileMetaData item, List<Path> l) {
        Path p = item.getPath();
        if(!getUseAbsolutePaths()) {
            p = p.getFileName();
        }
        final Path file = p;
        Boolean inList = l.stream().anyMatch(listPath -> file.equals(listPath));
        //XNOR
        return inList == getInclude();
    }

    @Override
    public String getFXMLPath() {
        return fxmlPath;
    }

    @Override
    public String getDescription() {
        return "compare to files in a list";
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(
            FilterByList.class.getCanonicalName() 
            + ",");
        s = s.append(include).append(",");
        s = s.append(useAbsolutePaths).append(",");
        s = s.append(listLocation);
        return s.toString();
    }

    /**
     * @return the include
     */
    public Boolean getInclude() {
        return include;
    }

    /**
     * @return the useAbsolutePaths
     */
    public Boolean getUseAbsolutePaths() {
        return useAbsolutePaths;
    }

    /**
     * @return the listLocation
     */
    public Path getListLocation() {
        return listLocation;
    }

    @Override
    public UIField[] getFields() {
        UIField[] fields = new UIField[3];
        fields[0] = new FileField("List Location", listLocation);
        fields[1] = new FlagField("Include", include);
        fields[2] = new FlagField("Use AbsolutePaths", useAbsolutePaths);
        return fields;
    }
    
}
