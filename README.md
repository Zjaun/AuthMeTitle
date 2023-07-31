# AuthMeTitle
AuthMeTitle is a Minecraft plugin for AuthMe plugin that shows titles for Authenticated/Unregistered/Unauthenticated Players

# Demonstration

The titles shown here is the default setting per event (LoginEvent, Register Event, isAuthenticated, isRegistered). You can customize the color, style, and message of the screen title and the subtitle on the Configuration section below.

When a player is unregistered:

![image](https://github.com/Zjaun/AuthMeTitle/assets/91415509/87515893-36c8-4805-8834-15a432c9e5e5)

When a player is unauthenticated:

![image](https://github.com/Zjaun/AuthMeTitle/assets/91415509/a8afa334-5cdd-404b-a6cf-ea3002ce3baf)

When a player is authenticated:

![image](https://github.com/Zjaun/AuthMeTitle/assets/91415509/d5ac1a05-b853-4061-bc59-2d833a4b1dfb)

# Configuration

Please read the comments because they explain basically everything on the yaml file.

```yaml
# PLEASE ONLY MODIFY THE VALUES, DOUBLE CHECK IF KEYS/SECTIONS ARE CORRECT
# PLEASE ONLY MODIFY THE VALUES, DOUBLE CHECK IF KEYS/SECTIONS ARE CORRECT
# PLEASE ONLY MODIFY THE VALUES, DOUBLE CHECK IF KEYS/SECTIONS ARE CORRECT
#
# The authed section is for players that have been authenticated
authed:
  # Show title for authenticated players? Set to true if yes, false if not (booleans only)
  enabled: true
  # Number of milliseconds for the title to fade in
  fadeIn: 250
  # Number of milliseconds for the title to stay
  stay: 4500
  # Number of milliseconds for the title to fade out
  fadeOut: 250
  authed_screen:
    # Color for the screen title (case insensitive)
    # Options are aqua, black, blue, dark_aqua, dark_blue, dark_gray, dark_green, dark_purple,
    # dark_red, gold, gray, green, light_purple, red, white, and yellow.
    # If ever the value of color is not listed above, it will default to white
    color: GREEN
    # Style for the screen title (case insensitive)
    # Options are bold, italic, obfuscated, strikethrough, underlined, and normal
    # If ever the value of format is not listed above, it will default to normal formatting
    format: NORMAL
    # List of messages that will be shown to a player
    # If you want to include the player name, use name
    # For each entry, create a new line and start with -
    # Example: - Hello World!
    messages:
    - Hello, name
  authed_subtitle:
    # Color for the subtitle (case insensitive)
    # Options are aqua, black, blue, dark_aqua, dark_blue, dark_gray, dark_green, dark_purple,
    # dark_red, gold, gray, green, light_purple, red, white, and yellow.
    # If ever the value of color is not listed above, it will default to white
    color: YELLOW
    # Style for the subtitle (case insensitive)
    # Options are bold, italic, obfuscated, strikethrough, underlined, and normal
    # If ever the value of format is not listed above, it will default to normal formatting
    format: BOLD
    # List of messages that will be shown to a player
    # If you want to include the player name, use name
    # For each entry, create a new line and start with -
    # Example: - Hello World!
    messages:
    - Welcome aboard!
    - Stay kind, play fair!
    - Respect others, have fun!
    - Happy exploring!
    - Let the fun begin!
# The unauthed section is for players that are not authenticated
unauthed:
  # Show title for authenticated players? Set to true if yes, false if not (booleans only)
  enabled: true
  unauthed_screen:
    # Color for the screen title (case insensitive)
    # Options are aqua, black, blue, dark_aqua, dark_blue, dark_gray, dark_green, dark_purple,
    # dark_red, gold, gray, green, light_purple, red, white, and yellow.
    # If ever the value of color is not listed above, it will default to white
    color: RED
    # Style for the screen title (case insensitive)
    # Options are bold, italic, obfuscated, strikethrough, underlined, and normal
    # If ever the value of format is not listed above, it will default to normal formatting
    format: NORMAL
    # List of messages that will be shown to a player
    # If you want to include the player name, use name
    # For each entry, create a new line and start with -
    # Example: - Hello World!
    messages:
    - Hello, name
  unauthed_subtitle:
    # Color for the subtitle (case insensitive)
    # Options are aqua, black, blue, dark_aqua, dark_blue, dark_gray, dark_green, dark_purple,
    # dark_red, gold, gray, green, light_purple, red, white, and yellow.
    # If ever the value of color is not listed above, it will default to white
    color: YELLOW
    # Style for the subtitle (case insensitive)
    # Options are bold, italic, obfuscated, strikethrough, underlined, and normal
    # If ever the value of format is not listed above, it will default to normal formatting
    format: BOLD
    # List of messages that will be shown to a player
    # If you want to include the player name, use name
    # For each entry, create a new line and start with -
    # Example: - Hello World!
    messages:
    - Please login using /login
# The unreg section is for players that are not registered
unreg:
  # Show title for authenticated players? Set to true if yes, false if not (booleans only)
  enabled: true
  unreg_screen:
    # Color for the screen title (case insensitive)
    # Options are aqua, black, blue, dark_aqua, dark_blue, dark_gray, dark_green, dark_purple,
    # dark_red, gold, gray, green, light_purple, red, white, and yellow.
    # If ever the value of color is not listed above, it will default to white
    color: RED
    # Style for the screen title (case insensitive)
    # Options are bold, italic, obfuscated, strikethrough, underlined, and normal
    # If ever the value of format is not listed above, it will default to normal formatting
    format: NORMAL
    # List of messages that will be shown to a player
    # If you want to include the player name, use name
    # For each entry, create a new line and start with -
    # Example: - Hello World!
    messages:
    - Hello, name
  unreg_subtitle:
    # Color for the subtitle (case insensitive)
    # Options are aqua, black, blue, dark_aqua, dark_blue, dark_gray, dark_green, dark_purple,
    # dark_red, gold, gray, green, light_purple, red, white, and yellow.
    # If ever the value of color is not listed above, it will default to white
    color: YELLOW
    # Style for the subtitle (case insensitive)
    # Options are bold, italic, obfuscated, strikethrough, underlined, and normal
    # If ever the value of format is not listed above, it will default to normal formatting
    format: BOLD
    # List of messages that will be shown to a player
    # If you want to include the player name, use name
    # For each entry, create a new line and start with -
    # Example: - Hello World!
    messages:
    - Please register using /register
```

# Installation

1. You need to have [AuthMe](https://github.com/AuthMe/AuthMeReloaded) installed on your Paper server.
2. Simply drop the latest release on your plugin folder

# Bugs, Suggestions, and Improvements

For bugs and suggestions, open an issue at this repo. Future improvements of this plugin is the addition of commands so that server admins do not have to mess around with the config.yml file anymore.
