/*
 * Copyright 2018 Omer Faruk Kurt.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blogspot.ofarukkurt.leaffaces.component;

import com.blogspot.ofarukkurt.leaffaces.utils.LeafFaces;
import java.io.IOException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author Omer Faruk Kurt
 * @Created on date 06/09/2018 21:27:15
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".Button")
@ResourceDependencies({
    @ResourceDependency(library = "leaffaces", name = "easy-button/easy-button.css"),
    @ResourceDependency(library = "leaffaces", name = "easy-button/easy-button.js")})
public class Button extends UIComponentBase {

    protected enum PropertyKeys {

        icon, 
        title, 
        buttonId, 
        styleClass, 
        function
    }

    @Override
    public void encodeAll(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        UIComponent parent = this;

        while (!(parent instanceof Map)) {
            parent = parent.getParent();
        }

        Map mapComponent = (Map) parent;
        String mapVar = mapComponent.getWidgetVar();
        String icon = getIcon();
        String title = getTitle();
        String buttonId = getButtonId();
        String styleClass = getStyleClass();
        String function = getFunction();
        String[] parts = this.getClientId().split(":");
        String id = parts[parts.length - 1];

        String addAttribute = "";
        if (buttonId != null && title != null) {
            addAttribute = ",'" + title + "','" + buttonId + "','" + styleClass + "'";
        }

        String layer = "var " + buttonId + " = L.easyButton('" + icon + "', function(btn, map){"
                + function
                + "}" + addAttribute + ");";
                //+ "}" + addAttribute + ").addTo(" + mapVar + ");";

        writer.write(layer);

    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".Button";
    }

    public String getIcon() {
        return (String) getStateHelper().eval(PropertyKeys.icon);
    }

    public void setIcon(final String icon) {
        getStateHelper().put(PropertyKeys.icon, icon);
    }

    public String getTitle() {
        return (String) getStateHelper().eval(PropertyKeys.title);
    }

    public void setTitle(final String title) {
        getStateHelper().put(PropertyKeys.title, title);
    }

    public String getButtonId() {
        return (String) getStateHelper().eval(PropertyKeys.buttonId);
    }

    public void setButtonId(final String buttonId) {
        getStateHelper().put(PropertyKeys.buttonId, buttonId);
    }

    public String getStyleClass() {
        return (String) getStateHelper().eval(PropertyKeys.styleClass, "btn btn-default btn-circle");
    }

    public void setStyleClass(final String styleClass) {
        getStateHelper().put(PropertyKeys.styleClass, styleClass);
    }

    public String getFunction() {
        return (String) getStateHelper().eval(PropertyKeys.function);
    }

    public void setFunction(final String function) {
        getStateHelper().put(PropertyKeys.function, function);
    }

}
