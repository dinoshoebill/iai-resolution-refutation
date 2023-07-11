package ui;

import java.util.*;

public class Resolution {

    public Set<TreeSet<String>> clauses = new LinkedHashSet<>();
    public Set<TreeSet<String>> sos = new LinkedHashSet<>();
    public Map<TreeSet<String>, ArrayList<TreeSet<String>>> mapper = new LinkedHashMap<>();
    public String conclusionElement;
    public String pathToResolutionFile;
    public String pathToInput;
    public List<String> commands = new ArrayList<>();
    public Set<String> ogInput = new LinkedHashSet<>();
    public String method;
    public static Set<TreeSet<String>> usedClauses = new HashSet<>();
}
