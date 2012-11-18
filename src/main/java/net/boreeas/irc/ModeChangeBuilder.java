/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.boreeas.irc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Boreeas
 */
public class ModeChangeBuilder {

    private Map<Character, String> adding = new HashMap<Character, String>();
    private Map<Character, String> removing = new HashMap<Character, String>();

    public void addMode(char mode, String param) {
        adding.put(mode, param);
    }

    public void addMode(char mode) {
        addMode(mode, "");
    }

    public void removeMode(char mode, String param) {
        removing.put(mode, param);
    }

    public void removeMode(char mode) {
        removing.put(mode, "");
    }

    public Map<Character, String> getAdding() {
        return adding;
    }

    public Map<Character, String> getRemoving() {
        return removing;
    }

    public List<String> format() {

        List<String> result =
                     new ArrayList<String>((adding.size() + removing.size()) / 4 + 1); // 4 modes per lines

        int count = 1;
        StringBuilder modeBuilder = new StringBuilder("+");
        StringBuilder paramBuilder = new StringBuilder();

        for (Entry<Character, String> addingMode: adding.entrySet()) {

            if (count > 4) {

                result.add(modeBuilder.toString().trim() + " " + paramBuilder.toString().trim().replaceAll(" +", " "));
                count = 1;
                modeBuilder = new StringBuilder("+");
                paramBuilder = new StringBuilder();
            }

            modeBuilder.append(addingMode.getKey());
            paramBuilder.append(addingMode.getValue()).append(" ");

            count++;
        }

        if (count > 4) {

            result.add(modeBuilder.toString().trim() + " " + paramBuilder.toString().trim().replaceAll(" +", " "));
            count = 1;
            modeBuilder = new StringBuilder("-");
            paramBuilder = new StringBuilder();
        } else {
            modeBuilder.append("-");
        }

        for (Entry<Character, String> removingMode: removing.entrySet()) {

            if (count > 4) {

                result.add(modeBuilder.toString().trim() + " " + paramBuilder.toString().trim().replaceAll(" +", " "));
                count = 1;
                modeBuilder = new StringBuilder("-");
                paramBuilder = new StringBuilder();
            }

            modeBuilder.append(removingMode.getKey());
            paramBuilder.append(removingMode.getValue()).append(" ");

            count++;
        }


        result.add(modeBuilder + " " + paramBuilder);

        return result;
    }
}
