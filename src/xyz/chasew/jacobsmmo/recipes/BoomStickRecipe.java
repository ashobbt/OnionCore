package xyz.chasew.jacobsmmo.recipes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class BoomStickRecipe {
    public static ItemStack item = new ItemStack(Material.STICK);

    public ShapedRecipe getRec(Plugin thisPlugin) {
        // Our custom variable which we will be changing around.

        // The meta of the diamond sword where we can change the name, and properties of the item.
        ItemMeta meta = item.getItemMeta();

        // We will initialise the next variable after changing the properties of the sword

        // This sets the name of the item.
        meta.setDisplayName(ChatColor.GREEN + "BoomStick");

        // Set the meta of the sword to the edited meta.
        item.setItemMeta(meta);

        // create a NamespacedKey for your recipe
        NamespacedKey key = new NamespacedKey(thisPlugin, "boom_stick");

        // Create our custom recipe variable
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        // Here we will set the places. E and S can represent anything, and the letters can be anything. Beware; this is case sensitive.
        recipe.shape(" T ", " S ", " T ");

        // Set what the letters represent.
        // E = Emerald, S = Stick
        recipe.setIngredient('T', Material.TNT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }
}
