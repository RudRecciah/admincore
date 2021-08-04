# To contribute, make a PR.
### If merged, your PR will most likely appear in a future release.
To have your PR merged, it must meet the following criteria:
* All console logs should use the logger, not printLn
* All handled errors and caught exceptions must:
  * Provide an error code in the console, formatted as `ID`-`current_unix_epoch_in_milliseconds`
  * Tell admins to report bugs to [https://rudrecciah.dev/admincore/bugs](https://rudrecciah.dev/admincore/bugs).
  * Call logError() for Discord logging.
* All data the plugin saves must be saved in a file inside the ./data/`folder`/ directory, not the config. Never call saveConfig(). For `folder`, use sd for server/plugin data, rd for report data, pd for playerdata, and ad for appeal data.
* All sensitive information (tokens, passwords, etc.) must come from the config and must be left as empty strings in the config included in your PR. Obviously you can have them in your local config, but don't put them in the project's config.
* All dependencies must come from Maven's pom.xml, not added directly or with .CLASSPATH or something else.
  If you have questions, you can find my contact information [here](https://rudrecciah.dev/#contact)!
### Porting to other versions
If you wrote your code for all versions of Admincore, you'll need to port those changes to each Minecraft version Admincore supports. When porting to other versions, fork the version's branch and start working. Hoping the Spigot developers were sober when they wrote their code, you can assume your changes will work on other versions if Admincore builds on those versions, but testing on those versions is still recommended.
### Getting config version errors?
Manually create `./Admincore/data/sd/ver/OVERRIDE` (`OVERRIDE` is a file without an extension, not a directory). This will allow you to skip config version checking.
