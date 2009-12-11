package util.resources;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Provides access to .properties resources for Vooga
 * 
 * @author Cheney Tsai
 */
public class VoogaResources
{
    public static final String NOT_FOUND = "GUI String Not Found";
    private static final String DEFAULT_LANGUAGE = "English";
    private static String myLang = DEFAULT_LANGUAGE;
    private static final String DEFAULT_RESOURCE_FOLDER_PATH = "resources.";
    private static String Resource_Folder_Path = DEFAULT_RESOURCE_FOLDER_PATH;
    private static final String DEFAULT_GAME_PATH = "dukeopalypse.";
    private static String Game_Folder_Path = DEFAULT_GAME_PATH;
    private static ResourceBundle myGUIResources = ResourceBundle
            .getBundle(Resource_Folder_Path + myLang);
    private static ResourceBundle myActorFactoryResources = ResourceBundle
            .getBundle(Resource_Folder_Path + "ActorFactory");
    private static ResourceBundle myActionFactoryResources = ResourceBundle
            .getBundle(Resource_Folder_Path + "ActionFactory");
    private static ResourceBundle myConditionFactoryResources = ResourceBundle
            .getBundle(Resource_Folder_Path + "ConditionFactory");
    private static ResourceBundle myLanguageFactoryResources = ResourceBundle
            .getBundle(Resource_Folder_Path + "LanguageFactory");
    private static ResourceBundle myLevelFactoryResources = ResourceBundle
            .getBundle(Game_Folder_Path + Resource_Folder_Path + "LevelFactory");
    private static ResourceBundle myConstantsResources = ResourceBundle
            .getBundle(Resource_Folder_Path + "Constants");

    /**
     * Returns the value of key in ResourceBundle bundle
     * 
     * @param key
     *            The key to look up in the resources
     * @param bundle
     *            The specific resource bundle to look the key up in
     * @return a String value this key is mapped to in the resource bundle
     */
    private static String getValueFromResourceBundle(String key,
            ResourceBundle bundle)
    {
        try
        {
            return bundle.getString(key);
        } catch (MissingResourceException e)
        {
            return NOT_FOUND;
        }
    }

    /**
     * Returns the correct text to display in the GUI for this type of command
     * 
     * @param command
     *            the name of the String to look up in the Resources
     * @return the correct text to display in the GUI
     */
    public static String getDisplayString(String command)
    {
        return getValueFromResourceBundle(command, myGUIResources);
    }

    /**
     * Returns the correct constant attached to the particular command
     * 
     * @param command
     *            the name of the String to look up in the Resources
     * @return the correct constant value of the String
     */
    public static double getConstant(String command)
    {
        return Double.parseDouble(getValueFromResourceBundle(command,
                myConstantsResources));
    }

    /**
     * Returns the correct actor class referenced by this string
     * 
     * @param name
     *            the name of the String to look up in the ActorFactoryResources
     * @return the actor class this name references
     */
    public static String getActorFactoryReference(String name)
    {
        return getValueFromResourceBundle(name, myActorFactoryResources);
    }

    /**
     * Returns the correct action class referenced by this string
     * 
     * @param name
     *            the name of the String to look up in the
     *            ActionFactoryResources
     * @return the action class this name references
     */
    public static String getActionFactoryReference(String name)
    {
        return getValueFromResourceBundle(name, myActionFactoryResources);
    }

    /**
     * Returns the correct condition class referenced by this string
     * 
     * @param name
     *            the name of the String to look up in the
     *            ConditionFactoryResources
     * @return the condition class this name references
     */
    public static String getConditionFactoryReference(String name)
    {
        return getValueFromResourceBundle(name, myConditionFactoryResources);
    }

    /**
     * Returns the correct level file referenced by this string
     * 
     * @param name
     *            the name of the String to look up in the
     *            ConditionFactoryResources
     * @return the condition class this name references
     */
    public static String getLevelFactoryReference(String name)
    {
        return getValueFromResourceBundle(name, myLevelFactoryResources);
    }

    /**
     * Changes the game folder path
     * 
     * @param gameName
     *            the name of the game to look for resources for
     */
    public static void setGamePath(String gameName)
    {
        Game_Folder_Path = gameName + ".";
    }

    /**
     * Changes the language of the program
     * 
     * @param lang
     *            the name of the new .properties language file to use, not
     *            including the .properties file extension
     */
    private static void setLanguage(String lang)
    {
        myGUIResources = ResourceBundle.getBundle("resources." + lang);
        myLang = lang;
    }

    /**
     * Changes the language of the program
     * 
     * @param key
     *            a key in LanguageFactory.properties file to set the new
     *            language to the value of
     */
    public static void setLanguageFromKey(String key)
    {
        setLanguage(getLanguageFactoryReference(key));
    }

    /**
     * Changes the resource folder path of the program
     * 
     * @param path
     *            the path of the new Resource Folder directory to use, assume
     *            src as root
     */
    public static void setResourceFolderPath(String path)
    {
        Resource_Folder_Path = path;
    }

    /**
     * Returns the set of keys for all supported actors in the ActorFactory
     * resource file
     * 
     * @return the set of keys for all supported actors in the ActorFactory
     *         resource file
     */
    public static Set<String> getActorFactoryKeySet()
    {
        return myActorFactoryResources.keySet();
    }

    /**
     * Returns the set of keys for all supported actions in the ActionFactory
     * resource file
     * 
     * @return the set of keys for all supported actions in the ActionFactory
     *         resource file
     */
    public static Set<String> getActionsFactoryKeySet()
    {
        return myActionFactoryResources.keySet();
    }

    /**
     * Returns the set of keys for all supported conditions in the
     * ConditionFactory resource file
     * 
     * @return the set of keys for all supported conditions in the
     *         ConditionFactory resource file
     */
    public static Set<String> getConditionFactoryKeySet()
    {
        return myConditionFactoryResources.keySet();
    }

    /**
     * Returns the correct language.properties file referenced by this string
     * 
     * @param name
     *            the name of the String to look up in the
     *            LanguageFactoryResources
     * @return the language.properties class this name references
     */
    private static String getLanguageFactoryReference(String name)
    {
        return getValueFromResourceBundle(name, myLanguageFactoryResources);
    }

    /**
     * Returns the set of keys for all supported languages in the
     * LanguageFactory resource file
     * 
     * @return the set of keys for all supported languages in the
     *         LanguageFactory resource file
     */
    public static Set<String> getLanguageFactoryKeySet()
    {
        return myLanguageFactoryResources.keySet();
    }
}
