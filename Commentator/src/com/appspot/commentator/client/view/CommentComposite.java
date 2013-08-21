package com.appspot.commentator.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

public class CommentComposite extends Composite {

    private static CommentCompositeUiBinder uiBinder = GWT.create(CommentCompositeUiBinder.class);
    @UiField Tree tree;
    @UiField TreeItem rootTreeItem;

    interface CommentCompositeUiBinder extends UiBinder<Widget, CommentComposite> {
    }

    public CommentComposite(String comment) {
        initWidget(uiBinder.createAndBindUi(this));
        rootTreeItem.setText(comment);
    }

    @UiHandler("tree")
    void onTreeSelection(SelectionEvent<TreeItem> event) {
        rootTreeItem.addTextItem(rootTreeItem.getText());
        rootTreeItem.setText("");
        rootTreeItem.setState(false);
    }
    @UiHandler("tree")
    void onTreeOpen(OpenEvent<TreeItem> event) {
        rootTreeItem.setText(rootTreeItem.getChild(0).getText());
        rootTreeItem.removeItems();
        rootTreeItem.setSelected(true);
    }
}
