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
package com.blogspot.ofarukkurt.leaffaces.model.map;

/**
 *
 * @author Omer Faruk Kurt
 * @Created on date 05/09/2018 08:28:32
 */
public class Marker extends Overlay implements java.io.Serializable {

    private static final long serialVersionUID = 5726114443895845761L;

    private LatLng latlng;
    private String title;

    public Marker(LatLng latlng) {
        this.latlng = latlng;
    }

    public Marker(LatLng latlng, String title) {
        this.latlng = latlng;
        this.title = title;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
