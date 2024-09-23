package info.exac.envers_demo.common;

import info.exac.envers_demo.model.RevisionInfoEntity;
import org.hibernate.envers.RevisionListener;

public class AuditRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        RevisionInfoEntity revision = (RevisionInfoEntity) revisionEntity;
        revision.setRevisionAuthor(getUsername());
    }

    private String getUsername() {
        return "annoymous";
    }

}
