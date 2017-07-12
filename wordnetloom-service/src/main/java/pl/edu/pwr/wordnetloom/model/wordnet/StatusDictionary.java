package pl.edu.pwr.wordnetloom.model.wordnet;

import javax.persistence.Entity;

@Entity
public class StatusDictionary extends Dictionary {

    public StatusDictionary() {
    }

    public StatusDictionary(String value, String desc) {
        super(value, desc);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (description != null) {
            sb.append(" ( ").append(description).append(" )");
        }
        return sb.toString();
    }
}
