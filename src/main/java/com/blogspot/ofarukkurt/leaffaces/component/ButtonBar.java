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
 * @Created on date 19/07/2018 22:52:56
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".ButtonBar")
@ResourceDependencies({
    @ResourceDependency(library = "leaffaces", name = "easy-button/easy-button.css"),
    @ResourceDependency(library = "leaffaces", name = "sharelocation/L.Icon.FontAwesome.css"),
    @ResourceDependency(library = "leaffaces", name = "sharelocation/L.Icon.FontAwesome.js"),
    @ResourceDependency(library = "leaffaces", name = "sharelocation/Leaflet.LocationShare.js"),
    @ResourceDependency(library = "leaffaces", name = "print/easyPrint.js"),
    @ResourceDependency(library = "leaffaces", name = "easy-button/easy-button.js")})
public class ButtonBar extends UIComponentBase {

    protected enum PropertyKeys {

        printerFileName,
        printerExportOnly,
        printerHidden,
        printerHideControlContainer
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
        String buttons = "";
        String fileName = getPrinterFileName();
        boolean exportOnly = getPrinterExportOnly();
        boolean hidden = getPrinterHidden();
        boolean hideControlContainer = getPrinterHideControlContainer();

        for (UIComponent cmp : mapComponent.getChildren()) {
            switch (cmp.getClass().getSimpleName()) {
                case "Button":
                    Button button = (Button) cmp;
                    buttons += button.getButtonId() + ",";
                    break;
            }
        }

        if (buttons.length() > 0 && buttons.charAt(buttons.length() - 1) == ',') {
            buttons = buttons.substring(0, buttons.length() - 1);
        }

        String printer = "var printer = L.easyPrint({"
                //                + "tileLayer: " + tileId + ","
                + "sizeModes: ['Current', 'A4Landscape', 'A4Portrait'],"
                + "filename: '" + fileName + "',"
                + "exportOnly: " + exportOnly + ","
                + "hidden:" + hidden + ","
                + "hideControlContainer: " + hideControlContainer
                + "}).addTo(" + mapVar + ");";

        String fixedButton = "var baseMaps = L.easyButton('fa-clone', function(btn, map){"
                + "var display = $(\".leaflet-iconLayers\").css(\"display\");"
                + "if(display=='block'){"
                + "$(\".leaflet-iconLayers\").css(\"display\",\"none\");"
                + "}else{"
                + "$(\".leaflet-iconLayers\").css(\"display\",\"block\");"
                + "}"
                + "},'Altlıklar','altliklar');"
                + "var haritadaAra = L.easyButton('fa-search', function(btn, map){"
                + "ara()"
                + "},'Haritada Ara','haritadaAra');"
                + "var konumumuGoster = L.easyButton('fa fa-crosshairs', function(btn, map){"
                + "findMyLocation(" + mapVar + ")"
                + "},'Konum G�ster','konumGoster');";

        fixedButton += "var haritaYazdir = L.easyButton('fa-print', function(btn, map){"
                + "haritayiYazdir();"
                + "},'Yazdır','haritaYazdir','btn btn-flat btn-primary');";

        if (buttons.equals("")) {
            buttons = "baseMaps,haritadaAra,konumumuGoster,haritaYazdir";
        } else {
            buttons = "baseMaps,haritadaAra,konumumuGoster,haritaYazdir," + buttons;
        }

        String layer = "var buttons = [" + buttons + "];"
                + "L.easyBar(buttons, {position: 'bottomleft'}).addTo(" + mapVar + ");";

        writer.write(printer + fixedButton + layer);

    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".ButtonBar";
    }

    public String getPrinterFileName() {
        return (String) getStateHelper().eval(PropertyKeys.printerFileName, "map");
    }

    public void setPrinterFileName(final String printerFileName) {
        getStateHelper().put(PropertyKeys.printerFileName, printerFileName);
    }

    public boolean getPrinterExportOnly() {
        return (Boolean) getStateHelper().eval(PropertyKeys.printerExportOnly, true);
    }

    public void setPrinterExportOnly(final boolean printerExportOnly) {
        getStateHelper().put(PropertyKeys.printerExportOnly, printerExportOnly);
    }

    public boolean getPrinterHidden() {
        return (Boolean) getStateHelper().eval(PropertyKeys.printerHidden, true);
    }

    public void setPrinterHidden(final boolean printerHidden) {
        getStateHelper().put(PropertyKeys.printerHidden, printerHidden);
    }

    public boolean getPrinterHideControlContainer() {
        return (Boolean) getStateHelper().eval(PropertyKeys.printerHideControlContainer, true);
    }

    public void setPrinterHideControlContainer(final boolean printerHideControlContainer) {
        getStateHelper().put(PropertyKeys.printerHideControlContainer, printerHideControlContainer);
    }

}
