package com.embodme;

import com.bitwig.extension.controller.ControllerExtension;
import com.bitwig.extension.controller.api.*;

public class EraeTouchExtension extends ControllerExtension
{
   protected EraeTouchExtension(final EraeTouchExtensionDefinition definition, final ControllerHost host)
   {
      super(definition, host);
   }

   @Override
   public void init()
   {
      final ControllerHost host = getHost();

      final MidiIn midiIn1 = host.getMidiInPort(0);
      final NoteInput noteInput1 = midiIn1.createNoteInput("", "??????");
      noteInput1.setShouldConsumeEvents(true);

      final MidiIn midiIn2 = host.getMidiInPort(1);
      final NoteInput noteInput2 = midiIn2.createNoteInput("MPE", "??????");
      noteInput2.setShouldConsumeEvents(true);
      noteInput2.setUseExpressiveMidi(true,0, 48);

      final String[] yesNo = {"Yes", "No"};
      final SettableEnumValue shouldSendInit =
              host.getPreferences().getEnumSetting("Send initialization messages", "MPE", yesNo, "Yes");

      shouldSendInit.addValueObserver(newValue ->
      {
         mShouldSendInit = newValue.equalsIgnoreCase("Yes");

         if (mShouldSendInit && mDidRunInitTask)
         {
            sendInitializationMessages();
            sendPitchbendRange(mPitchBendRange);
         }
      });

      final SettableRangedValue bendRange =
              host.getPreferences().getNumberSetting("Pitch Bend Range", "MPE", 1, 96, 1, "", 48);

      bendRange.addRawValueObserver(range ->
      {
         mPitchBendRange = (int)range;
         noteInput2.setUseExpressiveMidi(true, 0, mPitchBendRange);

         if (mShouldSendInit && mDidRunInitTask)
         {
            sendPitchbendRange(mPitchBendRange);
         }
      });

      host.scheduleTask(() ->
      {
         mDidRunInitTask = true;

         if (mShouldSendInit)
         {
            sendInitializationMessages();
         }
      }, 2000);
   }

   void sendInitializationMessages()
   {
      final MidiOut midiOut = getHost().getMidiOutPort(0);

      // Set up MPE mode: Zone 1 15 channels
      midiOut.sendMidi(0xB0, 101, 0); // Registered Parameter Number (RPN) - MSB*
      midiOut.sendMidi(0xB0, 100, 6); // Registered Parameter Number (RPN) - LSB*
      midiOut.sendMidi(0xB0, 6, 15);
      midiOut.sendMidi(0xB0, 38, 0);

      // Set up MPE mode: Zone 2 off
      midiOut.sendMidi(0xBF, 101, 0);
      midiOut.sendMidi(0xBF, 100, 6);
      midiOut.sendMidi(0xBF, 6, 0);
      midiOut.sendMidi(0xBF, 38, 0);
   }

   void sendPitchbendRange(int range)
   {
      final MidiOut midiOut = getHost().getMidiOutPort(0);

      // Set up pitch bend range
      midiOut.sendMidi(0xB0, 101, 0); // Registered Parameter Number (RPN) - MSB*
      midiOut.sendMidi(0xB0, 100, 0); // Registered Parameter Number (RPN) - LSB*
      midiOut.sendMidi(0xB0, 6, range);
      midiOut.sendMidi(0xB0, 38, 0);
   }

   @Override
   public void exit() {}

   @Override
   public void flush() {}

   private boolean mShouldSendInit = false;
   private boolean mDidRunInitTask = false;
   private int mPitchBendRange = 48;
}
