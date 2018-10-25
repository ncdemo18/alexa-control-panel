package com.netcracker.alexa.controlpanel.vaadin;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Push
@StyleSheet("style.css")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class NCLayout extends VerticalLayout implements RouterLayout {
    public NCLayout() {
        setClassName("nc-background-image");
    }
}
