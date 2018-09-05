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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Omer Faruk Kurt
 * @Created on date 05/09/2018 08:33:45
 */
public class DefaultMapModel implements MapModel, java.io.Serializable {

    private static final long serialVersionUID = 1883997201830149700L;

    private List<Marker> markers;

    public DefaultMapModel() {
        markers = new ArrayList<>();
    }

    @Override
    public void addOverlay(Overlay overlay) {
        if (overlay instanceof Marker) {
            overlay.setId("marker" + UUID.randomUUID().toString());
            markers.add((Marker) overlay);
        }
    }

    @Override
    public List<Marker> getMarkers() {
        return markers;
    }

}
