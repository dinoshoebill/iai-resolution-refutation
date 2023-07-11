package ui.resolution;

import ui.Resolution;

import java.util.*;

public class ResolutionMethod {

    public static boolean resolution(Resolution struct) {

        deletionMethodA(struct);
        return resolutionMethod(struct);
    }

    public static boolean resolutionMethod(Resolution struct) {

        int x = 0;
        do {

            List<TreeSet<String>> sosList = new ArrayList<>(struct.sos);

            List<String> sosElement = new ArrayList<>(sosList.get(x));

            Set<TreeSet<String>> union = new LinkedHashSet<>();
            union.addAll(struct.clauses);
            union.addAll(struct.sos);

            union.remove(sosList.get(x));

            for(String element : sosElement) {

                List<TreeSet<String>> toAddAfterLoop = new ArrayList<>();

                for(TreeSet<String> other : union) {

                    if(element.startsWith("~")) {
                        if(other.contains(element.substring(1))) {

                            TreeSet<String> toAdd = new TreeSet<>();

                            toAdd.addAll(other);
                            toAdd.addAll(sosElement);

                            toAdd.remove(element);
                            toAdd.remove(element.substring(1));

                            TreeSet<String> save1 = new TreeSet<>(sosElement);
                            TreeSet<String> save2 = new TreeSet<>(other);

                            ArrayList<TreeSet<String>> value = new ArrayList<>();
                            value.add(save1);
                            value.add(save2);

                            TreeSet<String> key = new TreeSet<>();

                            if(toAdd.size() == 0) {
                                key.add("NIL");
                                struct.mapper.put(key, value);

                                return true;
                            } else {
                                struct.mapper.put(toAdd, value);
                                toAddAfterLoop.add(toAdd);
                            }
                        }
                    } else {
                        if(other.contains("~" + element)) {

                            TreeSet<String> toAdd = new TreeSet<>();

                            toAdd.addAll(other);
                            toAdd.addAll(sosElement);

                            toAdd.remove(element);
                            toAdd.remove("~" + element);

                            TreeSet<String> save1 = new TreeSet<>(sosElement);

                            ArrayList<TreeSet<String>> value = new ArrayList<>();
                            value.add(save1);
                            value.add(other);

                            TreeSet<String> key = new TreeSet<>();

                            if(toAdd.size() == 0) {
                                key.add("NIL");
                                struct.mapper.put(key, value);
                                return true;
                            } else {
                                struct.mapper.put(toAdd, value);
                                toAddAfterLoop.add(toAdd);
                            }
                        }
                    }
                }

                struct.sos.addAll(toAddAfterLoop);
            }

            deletionMethodA(struct);

            x++;
        } while(x < struct.sos.size());

        return false;
    }

    public static void deletionMethodA(Resolution struct) {

        // put clauses and sos into one list
        List<TreeSet<String>> intersectList = new ArrayList<>();

        intersectList.addAll(struct.clauses);
        intersectList.addAll(struct.sos);

        int i = 0;
        do {
            // get set from list
            Set<String> element = intersectList.get(i);

            // for all other sets in list
            for(int j = i + 1; j < intersectList.size(); j++) {
                Set<String> another = intersectList.get(j);

                // find if another set contains all elements from current set
                if(element.size() > another.size()) {
                    if(element.containsAll(another)) {
                        // remove set
                        if(!struct.usedClauses.contains(element)) {
                            intersectList.remove(i);
                            // after deletion next element will fill current spot, i must remain unchanged
                            i--;
                            break;
                        }

                    }
                } else {
                    if(another.containsAll(element)) {
                        // remove set
                        if(!struct.usedClauses.contains(another)) {
                            intersectList.remove(j);
                            // after deletion next element will fill current spot, i must remain unchanged
                            i--;
                            break;
                        }
                    }
                }
            }

            // if no sets were found, continue searching
            i++;
        } while(i < intersectList.size());

        // put surviving elements in sets they originally belonged to
        struct.clauses.retainAll(intersectList);

        deletionMethodB(struct);
    }

    public static void deletionMethodB(Resolution struct) {

        LinkedHashSet<TreeSet<String>> intersect = new LinkedHashSet<>();

        intersect.addAll(struct.clauses);
        intersect.addAll(struct.sos);

        // for each set
        for(Iterator<TreeSet<String>> it1 = intersect.iterator(); it1.hasNext();) {
            Set<String> tmpSet = it1.next();

            for (String tmp : tmpSet) {
                if (tmp.startsWith("~")) {
                    if (tmpSet.contains(tmp.substring(1))) {
                        it1.remove();
                        break;
                    }
                } else {
                    if (tmpSet.contains("~" + tmp)) {
                        it1.remove();
                        break;
                    }
                }
            }
        }

        struct.clauses.retainAll(intersect);
        struct.sos.retainAll(intersect);
    }
}
