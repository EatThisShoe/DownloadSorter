/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.model.Rule;
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
public class AllRules {
    static Path ruleDir = Paths.get("./src/downloadsorter/model");
    List<Rule> rules;
    
    private AllRules() {
        rules = new ArrayList<>();
    }
    
    public List<Rule> getRules() {
        List<Rule> rules = new ArrayList<>();
        if (rules.isEmpty()) {
            rules = getRuleList(Rule.class);
        }
        return rules;
    }
    
    private <T extends Rule> List<T> getRuleList(Class<T> ruleType) {
        List<T> rules = new ArrayList<>();
        try {
            List<String> classNames = Files.list(ruleDir)
                .map(path -> path.getFileName().toString())
                .filter((String s) -> s.length() > 4 && s.endsWith(".java"))
                .map(s -> s.substring(0, s.length() - 5))
                .map(s -> "downloadsorter.model.".concat(s))
                .collect(Collectors.toList());
            for(String className: classNames) {
                try {
                    Class cl = Class.forName(className);
                    Object o = cl.newInstance();
                    if(ruleType.isAssignableFrom(o.getClass())){
                        T rule = (T) o;
                        rules.add(rule);
                    }
                } catch (Exception e) {}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rules;
    }
    
    public static AllRules getInstance() {
        return AllRulesHolder.INSTANCE;
    }

    
    
    private static class AllRulesHolder {
        private static final AllRules INSTANCE = new AllRules();
    }
}
