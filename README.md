This is an IRCBot, focused on being modular, easy to extend, and easy to update without restarting. Plugins interface with the bot in two ways:

**Commands** can be registered by plugins, and are automatically recognized by the bot from private messages and notices. A plugin is required to declare a _command prefix_, which prevents command collisions. Example: The core module uses the prefix '!', the bot uses '$' to recognize commands, so the core mute command is triggered as '$! mute'.

**Events** are fired by the bot on different occasions. Plugins can register event listeners to provide options that can't be covered by commands.

The bot requires a Core module to start, to enable restartless updating even of core features. Any module with a filename that matches Core(\..*)? can be used as such a module.
A default implementation can be found at https://github.com/Boreeas/ircbot-core. Core modules can define any command and feature, but are encouraged to provide the following functionality:
* Join/Leave channels
* Stop the bot
* Manage plugins
* Manage access levels
* Mute the bot on a per-channel basis
* Send raw text to the netword
* Access the help texts for plugins and commands