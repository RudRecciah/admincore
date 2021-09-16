# Admincore
### An indev core for Spigot and Paper server administration.

**Status:**

[![Auto Port](https://github.com/RudRecciah/admincore/actions/workflows/port.yml/badge.svg)](https://github.com/RudRecciah/admincore/actions/workflows/port.yml)
[![Build Nigtly](https://github.com/RudRecciah/admincore/actions/workflows/nightly.yml/badge.svg)](https://github.com/RudRecciah/admincore/actions/workflows/nightly.yml)
[![Build Production](https://github.com/RudRecciah/admincore/actions/workflows/production.yml/badge.svg)](https://github.com/RudRecciah/admincore/actions/workflows/production.yml)

**Current Features (0.6.0):**
* Support for Minecraft Versions:
  * 1.17.x (Native 1.17.1)
  * 1.16.x (Native 1.16.5)
  * 1.15.x (Native 1.15.2)
  * 1.14.x (Native 1.14.4)
  * 1.13.x (Native 1.13.2)
  * 1.12.x (Native 1.12.2)
* Announcement System
* Error Logging System
  * Logging to console
  * Logging to file
  * Logging to Discord
* Player Freeze system
* Punishment Logging System
  * Logging to file
  * Logging to Discord
* Player Reporting System
* Staff Report Management System
  * Report reviewing
  * Report closing
* Server Status Checker
* Staffchat With Notification System
* Staffmode:
  * Hides you from all non-staff players
  * Sends puesdo-join and leave messages
  * Player Management GUI:
    * Report players (also via /report)
    * Mute Players (also via /mute)
    * Ban Players (also via /ban)
    * IP Ban Players (also via /ban)
    * Tempban Players (also via /tempban)
    * Check Player Aliases (also via /alias)
    * Check Player Inventory
    * Check Player IPQS Statistics (IP address information and threat potential)
    * Check Past Punishments (also via /history)
* Update Checker
* Config Incompatibility Checker
* Discord Integration
  * Punishment logging (webhook)
  * Report logging (webhook)
  * Error logging (webhook)
  * Warning logging (webhook)
  * Staffchat (bot to send messages, webhook to receive messages)
  * Alias checking (bot)
  * Announcement sending (bot)
  * Player freezing (bot)
  * Player punishment history checking (bot)
  * Player IPQS statistic checking (bot)
* Player Appeal System
  * HTTP Server
  * Appeals For All Punishments
  * Appeal Acceptation/Rejection
  * Punishment Management (Control Punishment Based On Appeal Acceptance)
* Broadcast System (Updates And Messages From Admincore)
* Highly Customizable Permissions
* And More!

**Roadmap (For a more accurate and complete roadmap, check out the GitHub project for Admincore!):**
* 0.7.0
  * Documentation
  * Beginning of Public Beta
* 0.8.0
  * Bungeecord Support
  * Bungeecord Documentation
* 0.9.0
  * Final Touches
* 1.0.0
  * Release
