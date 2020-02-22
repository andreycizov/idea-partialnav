# Partial Navigation

A plugin that allows one to set how many lines each Page Up/Down button push move the caret

Idea stolen from here: https://stackoverflow.com/questions/27610249/intellij-idea-control-page-up-page-down-scroll-size

## Installation
The plugin is now in [IntelliJ IDEA plugin repository](https://plugins.jetbrains.com/plugin/11952-partial-navigation/).

1. Go to **Settings/Plugins**.
2. Search for "Partial Navigation".
 
## Installation from last source
 1.  Download the latest .jar from the [releases](https://github.com/andreycizov/idea-partialnav/releases) section.
 2.  Use the steps from "Install plugin from disk" section [here](https://www.jetbrains.com/help/idea/managing-plugins.html):
      -  In the **Settings/Preferences** dialog (`Ctrl+Alt+S`), select **Plugins**.
      -  In the **Plugins** dialog, click ![The cogwheel](https://www.jetbrains.com/help/img/idea/2018.3/artwork.studio.icons.logcat.toolbar.settings@2x.png) icon and then click **Install Plugin from Disk**.
      -  Select the plugin archive file and click **OK**.
      -  Click **OK** to apply the changes and restart the IDE if prompted.

## Configuration
To reconfigure keyboard shortcuts to use actions from this plugin: 
   -  You can read [here](https://www.jetbrains.com/help/idea/configuring-keyboard-and-mouse-shortcuts.html) how to reconfigure they keymap.
   -  The actions of this plugin are:
       - **Partial Page Down** - `Page Down`
       - **Partial Page Down with Selection** - `Shift` + `Page Down`
       - **Partial Page Up** - `Page Up`
       - **Partial Page Up with Selection** - `Shift` + `Page Up`
   -  On **Partial Navigation** settings page you can specify the number of lines the Page Up/Down should move the caret
   is in percentage of the screen with **Percent navigation** or specific number of lines with **Static navigation** 
   -  (Optional) Go crazy and set the multiplier to 1.5: that's 1.5 screens per key press.


## License

`Partial Navigation` is licensed under either of

 * Apache License, Version 2.0, ([LICENSE-APACHE](LICENSE-APACHE) or
   http://www.apache.org/licenses/LICENSE-2.0)

at your option.

### Contribution

Unless you explicitly state otherwise, any contribution intentionally submitted
for inclusion in Partial Navigation by you, as defined in the Apache-2.0 license, shall be
dual licensed as above, without any additional terms or conditions.
