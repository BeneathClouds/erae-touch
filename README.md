# Erae Touch

Bitwig controller extension for the [Erae Touch](https://www.embodme.com).

## Prerequisites

- Bitwig Studio >= 4.3
- Erae Firmware >= 1.2.0

## Installation

1. Disconnect the Erae Touch from your computer
2. Download the latest [EraeTouch.bwextension][latest] file from this repository
3. Open Bitwig Studio and go to **Settings** -> **Controllers**
4. Drag and drop the [EraeTouch.bwextension][latest] file onto **Settings** -> **Controllers**
5. Connect the Erae Touch to your computer
6. Click on the newly detected "**Embodme Erae Touch**" controller entry
7. Your Erae Touch should now be ready to use

## Configuration

Normally the Erae Touch should be automatically detected and require no further configuration. However, in the case this does not happen, set the following configuration MIDI input options:

1. Set MIDI input **1** to "**Erae Touch MIDI**"
2. Set MIDI input **2** to "**Erae Touch MIDI (MPE)**"
3. Set MIDI output **1** to "**Erae Touch MIDI**"

The MPE section can be left with the default settings:

- Send initialization messages: **Yes**
- Pitch Bend Range: **48**

[latest]: https://github.com/BeneathClouds/erae-touch/releases/download/v0.1/EraeTouch.bwextension
