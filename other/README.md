# Admincore Origin Data
### This folder contains all of the data Admincore grabs from its repository (other than the webhook icons)

BROADCAST:
* Stores the current broadcast. If this is different from the broadcast stored locally in the plugin's data, the plugin knows there's a new broadcast and will send the message in the console.

PLUGIN_VERSION:
* Stores the newest plugin version. If there is no OVERRIDE file in `./data/sd/ver/`, the version will be checked against the version of the plugin. If the versions don't match, the plugin knows there is a new version and will ask the server owner to update Admincore in the console.

data.md:
* Stores `./data/`'s README.
