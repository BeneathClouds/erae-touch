package com.embodme;
import java.util.UUID;

import com.bitwig.extension.api.PlatformType;
import com.bitwig.extension.controller.AutoDetectionMidiPortNamesList;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;

public class EraeTouchExtensionDefinition extends ControllerExtensionDefinition
{
   private static final UUID DRIVER_ID = UUID.fromString("7ccb5f8e-d9bd-40c1-b5a7-51553f019a43");

   public EraeTouchExtensionDefinition() {}

   @Override
   public String getName()
   {
      return "Erae Touch";
   }

   @Override
   public String getAuthor()
   {
      return "BeneathClouds";
   }

   @Override
   public String getVersion()
   {
      return "0.1";
   }

   @Override
   public UUID getId()
   {
      return DRIVER_ID;
   }

   @Override
   public String getHardwareVendor()
   {
      return "Embodme";
   }

   @Override
   public String getHardwareModel()
   {
      return "Erae Touch";
   }

   @Override
   public int getRequiredAPIVersion()
   {
      return 17;
   }

   @Override
   public int getNumMidiInPorts()
   {
      return 2;
   }

   @Override
   public int getNumMidiOutPorts()
   {
      return 1;
   }

   @Override
   public void listAutoDetectionMidiPortNames(final AutoDetectionMidiPortNamesList list, final PlatformType platformType)
   {
      list.add(new String[]{"Erae Touch MIDI", "Erae Touch MIDI (MPE)"}, new String[]{"Erae Touch MIDI"});
   }

   @Override
   public EraeTouchExtension createInstance(final ControllerHost host)
   {
      return new EraeTouchExtension(this, host);
   }
}
