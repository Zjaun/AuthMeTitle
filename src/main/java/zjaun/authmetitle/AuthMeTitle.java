package zjaun.authmetitle;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fr.xephi.authme.api.v3.AuthMeApi;
import fr.xephi.authme.events.LoginEvent;
import fr.xephi.authme.events.RegisterEvent;
import fr.xephi.authme.events.UnregisterByPlayerEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;

public class AuthMeTitle extends JavaPlugin implements Listener {

    File configFile = new File(getDataFolder(), "config.yml");
    Boolean configMissing = false;
    YamlConfiguration config;


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = event.getPlayer().getName();
        AuthMeApi authMeApi = AuthMeApi.getInstance();
        if (!authMeApi.isRegistered(playerName)) {
            unregPlayer(playerName, player);
        } else if (!authMeApi.isAuthenticated(player)) {
            unauthedPlayer(playerName, player);
        }
    }

    @EventHandler
    public void onPlayerUnreg(UnregisterByPlayerEvent event) {
        Player player = event.getPlayer();
        String playerName = event.getPlayer().getName();
        unregPlayer(playerName, player);
    }

    @EventHandler
    public void onPlayerAuth(LoginEvent event) {
        Player player = event.getPlayer();
        String playerName = event.getPlayer().getName();
        player.clearTitle();
        authedPlayer(playerName, player);
    }

    @EventHandler
    public void onPlayerReg(RegisterEvent event) {
        Player player = event.getPlayer();
        String playerName = event.getPlayer().getName();
        player.clearTitle();
        authedPlayer(playerName, player);
    }

    private void unregPlayer(String playerName, Player player) {
        config = YamlConfiguration.loadConfiguration(configFile);
        if (!config.getBoolean("unreg.enabled")) return;
        String mainTitleMessage = mainTitle(playerName, unregMessagesScreen());
        String subTitleMessage = subTitle(playerName, unregMessagesSubtitle(), "unregPlayer");
        NamedTextColor mainColor = color("unreg.unreg_screen.color");
        NamedTextColor subColor = color("unreg.unreg_subtitle.color");
        Component mainTitle;
        Component subTitle;
        TextDecoration mainDecor;
        TextDecoration subDecor;
        Optional<TextDecoration> mainDecorOption = decor("unreg.unreg_screen.format");
        Optional<TextDecoration> subDecorOption = decor("unreg.unreg_subtitle.format");
        if (mainDecorOption.isPresent()) {
            mainDecor = mainDecorOption.get();
            mainTitle = Component.text(mainTitleMessage, mainColor, mainDecor);
        } else {
            mainTitle = Component.text(mainTitleMessage, mainColor);
        }
        if (subDecorOption.isPresent()) {
            subDecor = subDecorOption.get();
            subTitle = Component.text(subTitleMessage, subColor, subDecor);
        } else {
            subTitle = Component.text(subTitleMessage, subColor);
        }
        Times time = Times.times(Duration.ofMillis(250), Duration.ofDays(7), Duration.ofMillis(250));
        Title title = Title.title(mainTitle, subTitle, time);
        player.showTitle(title);
    }

    private void unauthedPlayer(String playerName, Player player) {
        config = YamlConfiguration.loadConfiguration(configFile);
        if (!config.getBoolean("unauthed.enabled")) return;
        String mainTitleMessage = mainTitle(playerName, unauthedMessagesScreen());
        String subTitleMessage = subTitle(playerName, unauthedMessagesSubtitle(), "unregPlayer");
        NamedTextColor mainColor = color("unauthed.unauthed_screen.color");
        NamedTextColor subColor = color("unauthed.unauthed_subtitle.color");
        TextDecoration mainDecor;
        TextDecoration subDecor;
        Optional<TextDecoration> mainDecorOption = decor("unauthed.unauthed_screen.format");
        Optional<TextDecoration> subDecorOption = decor("unauthed.unauthed_subtitle.format");
        Component mainTitle;
        Component subTitle;
        if (mainDecorOption.isPresent()) {
            mainDecor = mainDecorOption.get();
            mainTitle = Component.text(mainTitleMessage, mainColor, mainDecor);
        } else {
            mainTitle = Component.text(mainTitleMessage, mainColor);
        }
        if (subDecorOption.isPresent()) {
            subDecor = subDecorOption.get();
            subTitle = Component.text(subTitleMessage, subColor, subDecor);
        } else {
            subTitle = Component.text(subTitleMessage, subColor);
        }
        Times time = Times.times(Duration.ofMillis(250), Duration.ofDays(7), Duration.ofMillis(250));
        Title title = Title.title(mainTitle, subTitle, time);
        player.showTitle(title);
    }

    private void authedPlayer(String playerName, Player player) {
        config = YamlConfiguration.loadConfiguration(configFile);
        if (!config.getBoolean("authed.enabled")) return;
        String mainTitleMessage = mainTitle(playerName, authedMessagesScreen());
        String subTitleMessage = subTitle(playerName, authedMessagesSubtitle(), "authedPlayer");
        long fadeIn = config.getLong("authed.fadeIn");
        long stay = config.getLong("authed.stay");
        long fadeOut = config.getLong("authed.fadeOut");
        NamedTextColor mainColor = color("authed.authed_screen.color");
        NamedTextColor subColor = color("authed.authed_subtitle.color");
        TextDecoration mainDecor;
        TextDecoration subDecor;
        Optional<TextDecoration> mainDecorOption = decor("authed.authed_screen.format");
        Optional<TextDecoration> subDecorOption = decor("authed.authed_subtitle.format");
        Component mainTitle;
        Component subTitle;
        if (mainDecorOption.isPresent()) {
            mainDecor = mainDecorOption.get();
            mainTitle = Component.text(mainTitleMessage, mainColor, mainDecor);
        } else {
            mainTitle = Component.text(mainTitleMessage, mainColor);
        }
        if (subDecorOption.isPresent()) {
            subDecor = subDecorOption.get();
            subTitle = Component.text(subTitleMessage, subColor, subDecor);
        } else {
            subTitle = Component.text(subTitleMessage, subColor);
        }
        Times time = Times.times(Duration.ofMillis(fadeIn), Duration.ofMillis(stay), Duration.ofMillis(fadeOut));
        Title title = Title.title(mainTitle, subTitle, time);
        player.showTitle(title);
    }

    private String mainTitle(String playerName, List<String> messages) {
        String mainTitle;
        Random random = new Random();
        if (messages.size() == 0) {
            mainTitle = "WELCOME, " + playerName;
        } else {
            mainTitle = messages.get(random.nextInt(messages.size())).replace("name", playerName);
        }
        return mainTitle;
    } 

    private String subTitle(String playerName, List<String> messages, String method) {
        String subTitle = null;
        Random random = new Random();
        switch (method) {
            case "unregPlayer":
                if (messages.size() != 0) {
                    subTitle = messages.get(random.nextInt(messages.size())).replace("name", playerName);
                } else {
                    subTitle = "Please register using /register";
                }
            break;
            case "unauthedPlayer":
                if (messages.size() != 0) {
                    subTitle = messages.get(random.nextInt(messages.size())).replace("name", playerName);
                } else {
                    subTitle = "Please login using /login";
                }
            break;
            case "authedPlayer":
                if (messages.size() != 0) {
                    subTitle = messages.get(random.nextInt(messages.size())).replace("name", playerName);
                } else {
                    subTitle = "Enjoy!";
                }
            break;
        }
        return subTitle;
    } 

    private void load() { // so that you can use this plugin immediately (ex. when loading using plugmanx, all unregistered/unauthenticated players will see the message immediately)
        AuthMeApi authMeApi = AuthMeApi.getInstance();
        for (Player player : Bukkit.getOnlinePlayers()) {
            String playerName = player.getName();
            if (!authMeApi.isRegistered(playerName)) {
                unregPlayer(playerName, player);
            } else if (!authMeApi.isAuthenticated(player)) {
                unauthedPlayer(playerName, player);
            }
        }
    }

    private NamedTextColor color(String configSection) {
        String color = config.getString(configSection);
        if (color == null) {
            return NamedTextColor.WHITE;
        } else {
            switch (color.toLowerCase()) {
                case "aqua":
                    return NamedTextColor.AQUA;
                case "black":
                    return NamedTextColor.BLACK;
                case "blue":
                    return NamedTextColor.BLUE;
                case "dark_aqua":
                    return NamedTextColor.DARK_AQUA;
                case "dark_blue":
                    return NamedTextColor.DARK_BLUE;
                case "dark_gray":
                    return NamedTextColor.DARK_GRAY;
                case "dark_green":
                    return NamedTextColor.DARK_GREEN;
                case "dark_purple":
                    return NamedTextColor.DARK_PURPLE;
                case "dark_red":
                    return NamedTextColor.DARK_RED;
                case "gold":
                    return NamedTextColor.GOLD;
                case "gray":
                    return NamedTextColor.GRAY;
                case "green":
                    return NamedTextColor.GREEN;
                case "light_purple":
                    return NamedTextColor.LIGHT_PURPLE;
                case "red":
                    return NamedTextColor.RED;
                case "white":
                    return NamedTextColor.WHITE;
                case "yellow":
                    return NamedTextColor.YELLOW;
                default:
                    return NamedTextColor.WHITE;
            }
        }
    }

    private Optional<TextDecoration> decor(String configSection) {
        String decor = config.getString(configSection);
        if (decor == null) {
            return Optional.empty();
        } else {
            switch (decor.toLowerCase()) {
                case "bold":
                    return Optional.of(TextDecoration.BOLD);
                case "italic":
                    return Optional.of(TextDecoration.ITALIC);
                case "obfuscated":
                    return Optional.of(TextDecoration.OBFUSCATED);
                case "strikethrough":
                    return Optional.of(TextDecoration.STRIKETHROUGH);
                case "underlined":
                    return Optional.of(TextDecoration.UNDERLINED);
                default:
                    return Optional.empty();
            }
        }
    }

    private void loadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        if (configMissing) {
            getLogger().info("Creating config.yml");
            configMissing = false;
        }
        config.addDefault("authed.enabled", true);
        config.addDefault("authed.fadeIn", 250);
        config.addDefault("authed.stay", 4500);
        config.addDefault("authed.fadeOut", 250);
        config.addDefault("authed.authed_screen.color", "GREEN");
        config.addDefault("authed.authed_screen.format", "NORMAL");
        config.addDefault("authed.authed_screen.messages", defaultAuthedMessagesScreen());
        config.addDefault("authed.authed_subtitle.color", "YELLOW");
        config.addDefault("authed.authed_subtitle.format", "BOLD");
        config.addDefault("authed.authed_subtitle.messages", defaultAuthedMessagesSubtitle());
        config.addDefault("unauthed.enabled", true);
        config.addDefault("unauthed.unauthed_screen.color", "RED");
        config.addDefault("unauthed.unauthed_screen.format", "NORMAL");
        config.addDefault("unauthed.unauthed_screen.messages", defaultUnauthedMessagesScreen());
        config.addDefault("unauthed.unauthed_subtitle.color", "YELLOW");
        config.addDefault("unauthed.unauthed_subtitle.format", "BOLD");
        config.addDefault("unauthed.unauthed_subtitle.messages", defaultUnauthedMessagesSubtitle());
        config.addDefault("unreg.enabled", true);
        config.addDefault("unreg.unreg_screen.color", "RED");
        config.addDefault("unreg.unreg_screen.format", "NORMAL");
        config.addDefault("unreg.unreg_screen.messages", defaultUnregMessagesScreen());
        config.addDefault("unreg.unreg_subtitle.color", "YELLOW");
        config.addDefault("unreg.unreg_subtitle.format", "BOLD");
        config.addDefault("unreg.unreg_subtitle.messages", defaultUnregMessagesSubtitle());
        config.options().copyDefaults(true);
        saveConfig(configFile);
    }

    private void saveConfig(File configFile) {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> defaultUnauthedMessagesScreen() {
        List<String> messages = new ArrayList<>();
        messages.add("Hello, name");
        return messages;
    }

    public List<String> defaultUnauthedMessagesSubtitle() {
        List<String> messages = new ArrayList<>();
        messages.add("Please login using /login");
        return messages;
    }

    public List<String> defaultUnregMessagesScreen() {
        List<String> messages = new ArrayList<>();
        messages.add("Hello, name");
        return messages;
    }

    public List<String> defaultUnregMessagesSubtitle() {
        List<String> messages = new ArrayList<>();
        messages.add("Please register using /register");
        return messages;
    }

    public List<String> defaultAuthedMessagesScreen() {
        List<String> messages = new ArrayList<>();
        messages.add("Hello, name");
        return messages;
    }

    public List<String> defaultAuthedMessagesSubtitle() {
        List<String> messages = new ArrayList<>();
        messages.add("Welcome aboard!");
        messages.add("Stay kind, play fair!");
        messages.add("Respect others, have fun!");
        messages.add("Happy exploring!");
        messages.add("Let the fun begin!");
        return messages;
    }

    public List<String> authedMessagesScreen() {
        config = YamlConfiguration.loadConfiguration(configFile);
        List<String> messages = config.getStringList("authed.authed_screen.messages");
        return messages;
    }

    public List<String> authedMessagesSubtitle() {
        config = YamlConfiguration.loadConfiguration(configFile);
        List<String> messages = config.getStringList("authed.authed_subtitle.messages");
        return messages;
    }

    public List<String> unauthedMessagesScreen() {
        config = YamlConfiguration.loadConfiguration(configFile);
        List<String> messages = config.getStringList("unauthed.unauthed_screen.messages");
        return messages;
    }

    public List<String> unauthedMessagesSubtitle() {
        config = YamlConfiguration.loadConfiguration(configFile);
        List<String> messages = config.getStringList("unauthed.unauthed_subtitle.messages");
        return messages;
    }

    public List<String> unregMessagesScreen() {
        config = YamlConfiguration.loadConfiguration(configFile);
        List<String> messages = config.getStringList("unreg.unreg_screen.messages");
        return messages;
    }

    public List<String> unregMessagesSubtitle() {
        config = YamlConfiguration.loadConfiguration(configFile);
        List<String> messages = config.getStringList("unreg.unreg_subtitle.messages");
        return messages;
    }


    @Override
    public void onEnable() {
        File folder = new File(getDataFolder(), "");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        configFile = new File(folder, "config.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            configMissing = true;
        }
        getServer().getPluginManager().registerEvents(this, this);
        load();
        loadConfig();
        getLogger().info("AuthMeTitle Loaded");
    }

    @Override
    public void onDisable() {
        getLogger().info("AuthMeTitle Disabled");
    }
}
