package pl.edu.pwr.wordnetloom.relationtype.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import pl.edu.pwr.wordnetloom.common.model.GraphPosition;
import pl.edu.pwr.wordnetloom.common.model.Localised;
import pl.edu.pwr.wordnetloom.lexicon.model.Lexicon;
import pl.edu.pwr.wordnetloom.relationtest.model.SenseRelationTest;

@Entity
@Table(name = "sense_relation_type")
public class SenseRelationType  implements Serializable, IRelationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinTable(
            name = "sense_relation_type_allowed_lexicons",
            joinColumns = @JoinColumn(name = "sense_relation_type_id"),
            inverseJoinColumns = @JoinColumn(name = "lexicon_id")
    )
    private Set<Lexicon> lexicons;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name_id")
    private Localised nameStrings = new Localised();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private Localised descriptionStrings = new Localised();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_text_id")
    private Localised displayStrings = new Localised();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "short_display_text_id")
    private Localised shortDisplayStrings = new Localised();

    @Basic
    @Column(name = "auto_reverse", columnDefinition = "bit default 0")
    private Boolean autoReverse;

    @OneToMany(mappedBy = "relationType", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SenseRelationTest> relationTests = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_relation_type_id", nullable = true)
    private SenseRelationType parent;

    @ManyToOne
    @JoinColumn(name = "reverse_relation_type_id", nullable = true)
    private SenseRelationType reverse;

    @Column(name = "graph_position")
    @Enumerated(EnumType.STRING)
    private GraphPosition graphPosition;

    private String color;

    public SenseRelationType(SenseRelationType parent, SenseRelationType reverse, Boolean autoReverse) {
        this.autoReverse = autoReverse;
        this.parent = parent;
        this.reverse = reverse;
    }

    public SenseRelationType() {
    }

    public String getName(String locale) {
        return this.nameStrings.getString(locale);
    }

    public void setName(String locale, String name) {
        this.nameStrings.addString(locale, name);
    }

    public String getDescription(String locale) {
        return this.descriptionStrings.getString(locale);
    }

    public void setDescription(String locale, String description) {
        this.descriptionStrings.addString(locale, description);
    }

    public String getDisplayText(String locale) {
        return this.displayStrings.getString(locale);
    }

    public void setDisplayText(String locale, String display) {
        this.displayStrings.addString(locale, display);
    }

    public String getShortDisplayText(String locale) {
        return this.shortDisplayStrings.getString(locale);
    }

    public void setShortDisplayText(String locale, String shortDisplay) {
        this.shortDisplayStrings.addString(locale, shortDisplay);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Lexicon> getLexicons() {
        return lexicons;
    }

    public void setLexicons(Set<Lexicon> lexicons) {
        this.lexicons = lexicons;
    }

    public List<SenseRelationTest> getRelationTests() {
        return relationTests;
    }

    public void setRelationTests(List<SenseRelationTest> relationTests) {
        this.relationTests = relationTests;
    }

    public Boolean getAutoReverse() {
        return autoReverse;
    }

    public void setAutoReverse(Boolean autoReverse) {
        this.autoReverse = autoReverse;
    }

    public SenseRelationType getParent() {
        return parent;
    }

    public void setParent(IRelationType parent) {
        assert parent instanceof SenseRelationType;
        this.parent = (SenseRelationType)parent;
    }

    public SenseRelationType getReverse() {
        return reverse;
    }

    public void setReverse(IRelationType reverse) {
        assert reverse instanceof SenseRelationType;
        this.reverse = (SenseRelationType)reverse;
    }

    public GraphPosition getGraphPosition() {
        return graphPosition;
    }

    public void setGraphPosition(GraphPosition graphPosition) {
        this.graphPosition = graphPosition;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean isMultilingual() {
        return false;
    }

    @Override
    public void setMultilingual(boolean multilingual) {
        // Nie robi nic
    }
}
