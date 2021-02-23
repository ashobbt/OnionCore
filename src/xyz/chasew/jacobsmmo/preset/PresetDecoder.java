package xyz.chasew.jacobsmmo.preset;

import xyz.chasew.jacobsmmo.preset.particles.ParticlePresetManifest;

import java.util.HashMap;

public class PresetDecoder {
    private HashMap<String, PresetManifest> manifestHashMap;
    public PresetDecoder() {
        this.manifestHashMap = new HashMap<String, PresetManifest>();
        registerPresetManifest("particles", new ParticlePresetManifest());
    }
    private void registerPresetManifest(String usableManifestName, PresetManifest manifest) {
        manifestHashMap.put(usableManifestName, manifest);
    }
    public PresetManifest useManifest(String manifestName) {
        return manifestHashMap.get(manifestName);
    }
}
