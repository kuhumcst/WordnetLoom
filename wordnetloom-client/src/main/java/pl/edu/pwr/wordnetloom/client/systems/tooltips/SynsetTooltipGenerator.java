package pl.edu.pwr.wordnetloom.client.systems.tooltips;

import pl.edu.pwr.wordnetloom.client.remote.RemoteConnectionProvider;
import pl.edu.pwr.wordnetloom.client.remote.RemoteService;
import pl.edu.pwr.wordnetloom.client.systems.managers.LexiconManager;
import pl.edu.pwr.wordnetloom.client.systems.managers.LocalisationManager;
import pl.edu.pwr.wordnetloom.common.model.NodeDirection;
import pl.edu.pwr.wordnetloom.sense.model.Sense;
import pl.edu.pwr.wordnetloom.sense.model.SenseAttributes;
import pl.edu.pwr.wordnetloom.synset.model.Synset;
import pl.edu.pwr.wordnetloom.synset.model.SynsetAttributes;
import pl.edu.pwr.wordnetloom.synsetrelation.model.SynsetRelation;

import java.util.List;

public class SynsetTooltipGenerator implements ToolTipGeneratorInterface{

    private ToolTipBuilder builder;

    public SynsetTooltipGenerator()
    {
        builder = new ToolTipBuilder();
    }

    @Override
    public String getToolTipText(Object object) {
        if (!hasEnabledTooltips()) {
            return null;
        }
        Synset synset = (Synset)object;
        Synset fetchedSynset = RemoteService.synsetRemote.fetchSynset(synset.getId());
        //TODO sprawdzić, czy wystarczy pobrać tylko relację, gdzie synset jest relacja
        List<SynsetRelation> synsetRelations = RemoteService.synsetRelationRemote.findRelationsWhereSynsetIsParent(synset, LexiconManager.getInstance().getUserChosenLexiconsIds(), NodeDirection.values());
        return getSenseToolTipText(fetchedSynset, synsetRelations);
    }

    private String getSenseToolTipText(Synset synset, List<SynsetRelation> relations) {
        builder.clear();
        //TODO zrobić dodawanie nazwy
        SynsetAttributes attributes = null ;//synset.getSynsetAttributes();
        if(attributes != null) {
            builder.addDefinition(attributes.getDefinition())
                    .addArtificial(attributes.getIsAbstract());
        } else {
            builder.addArtificial(false);
        }
        builder.addStatus(synset.getStatus());

        Sense headSense = synset.getSenses().get(0);
        builder.addDomain(headSense.getDomain());
        if(attributes != null) {
            builder.addOwner(attributes.getOwner())
                    .addSynsetComment(attributes.getComment());
        }
        SenseAttributes senseAttributes = headSense.getSenseAttributes();
        if(senseAttributes != null) {
            builder.addSenseComment(senseAttributes.getComment());
        }
        if(relations != null && !relations.isEmpty()) {
            builder.addSynsetRelations(relations);
        }

        return builder.buildText();
    }

    @Override
    public boolean hasEnabledTooltips() {
        return RemoteConnectionProvider.getInstance().getUser().getSettings().getShowToolTips();
    }
}
