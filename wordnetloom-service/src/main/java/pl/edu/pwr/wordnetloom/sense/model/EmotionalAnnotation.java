package pl.edu.pwr.wordnetloom.sense.model;

import org.hibernate.annotations.*;
import pl.edu.pwr.wordnetloom.common.model.GenericEntity;
import pl.edu.pwr.wordnetloom.dictionary.model.Markedness;
import pl.edu.pwr.wordnetloom.user.model.User;
import sun.security.acl.PermissionImpl;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "emotional_annotations")
public class EmotionalAnnotation extends GenericEntity {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sense_id")
    private Sense sense;

    @Column(name = "super_anotation")
    private boolean superAnnotation;

    @Column(name = "has_emotional_characteristic")
    private boolean emotionalCharacteristic;


    @OneToMany(mappedBy = "annotation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<UnitEmotion> emotions;

    @OneToMany(mappedBy = "annotation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<UnitValuation> valuations;

    @ManyToOne()
    @JoinColumn(name = "markedness_id")
    private Markedness markedness;

    private String example1;

    private String example2;

    @ManyToOne()
    @JoinColumn(name = "owner")
    private User owner;

    public Sense getSense() {
        return sense;
    }

    public boolean isSuperAnnotation() {
        return superAnnotation;
    }

    public boolean hasEmotionalCharacteristic() {
        return emotionalCharacteristic;
    }

    public Set<UnitEmotion> getEmotions() {
        return emotions;
    }

    public Set<UnitValuation> getValuations() {
        return valuations;
    }

    public Markedness getMarkedness() {
        return markedness;
    }

    public String getExample1() {
        return example1;
    }

    public String getExample2() {
        return example2;
    }

    public User getOwner() {
        return owner;
    }

    public void setSuperAnnotation(boolean superAnnotation){
        this.superAnnotation = superAnnotation;
    }

    public void setEmotionalCharacteristic(boolean emotionalCharacteristic) {
        this.emotionalCharacteristic = emotionalCharacteristic;
    }

    public void setEmotions(Set<UnitEmotion> emotions){
        this.emotions = emotions;
    }

    public void setValuations(Set<UnitValuation> valuations){
        this.valuations = valuations;
    }

    public void setMarkedness(Markedness markedness){
        this.markedness = markedness;
    }

    public void setExample1(String example){
        this.example1 = example;
    }

    public void setExample2(String example) {
        this.example2 = example;
    }

    public void setOwner(User owner){
        this.owner = owner;
    }
}
