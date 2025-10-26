package dev.boze.api.internal.interfaces;

import dev.boze.api.utility.ProfileHelper.ProfileCategory;

public interface IProfiles {
  
    String getSelectedProfile(ProfileCategory category);

    String[] getAvailableProfiles(ProfileCategory category);
} 