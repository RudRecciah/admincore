# Admincore data folder
### Do not touch these files or their values unless you know what you're doing!
If you do know what you're doing, here's some helpful information:
* `./ad/` stores appeal data.
  * Appeal data is saved in YAML files named by the ID of the appeal. The `Admincore Punishment Verifier thread` constantly checks each file to make sure the player who appealed is still punished, and will delete the file if not.
* `./pd/` stores playerdata.
  * Playerdata is saved in YAML files named by the UUID of the player. The IP of the player is updated every time they join, while the other data is only updated as needed.
* `./rd/` stores report data.
  * Report data is saved in YAML files named by the UUID of every player who's joined the server. The reports themselves are stored in `Configutation Sections` named by the index of the report.
* `./sd/` stores server data. This is the folder you're probably looking for.
  * `./log/` stores your `PUNISHMENT_LOGS.rtl` and `SILENT_ERRORS.rtl` files.
    * `PUNISHMENT_LOGS.rtl` stores a log of creation and removal of punishments.
    * `SILENT_ERRORS.rtl` stores a log of errors that won't cause issues with your server but are still worth logging. Don't worry about this file unless you're told to provide it when reporting a bug.
  * `./ver/` stores your plugin's local version, the plugin's origin version, and your `OVERRIDE` file if you have one. If the local version doesn't match the origin, you will receive a message in your console telling you Admincore has a new version available.
  * `./broadcasts/` stores messages and updates from Admincore. This is where the messages you may see about exciting features or other news are stored.
  * Server data is stored in a single YAML file named `data.yml`. The notification settings of players who, at one point, logged in with the `admincore.staff` permission are saved in this file. Their settings will still be saved in this file even if they join again without the permission. Even with notifications turned on, if a player doesn't have the permission, they won't receive messages from the staff or staffmode channels, meaning you don't have to touch this file when you demote a staff member.
