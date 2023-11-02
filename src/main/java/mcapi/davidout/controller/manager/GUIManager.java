package mcapi.davidout.controller.manager;

import mcapi.davidout.model.custom.gui.GUI;
import mcapi.davidout.utillity.text.TextUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public class GUIManager implements Listener {

    private List<GUI> guiList;

    public GUIManager() {
        this.guiList = new ArrayList<>();
    }

    public void addGUI(GUI gui) {this.guiList.add(gui);}
    public void removeGUI(int index) {this.guiList.remove(index);}
    public void removeGUI(String title) {
        this.guiList.forEach(gui -> {
            if(!gui.getTitle().equalsIgnoreCase(TextUtils.formatColorCodes(title))) return;
            this.guiList.remove(gui);
        });
    }


    /**
     *  Inventory click listener.
     */

    @EventHandler
    public void onGUIClick(InventoryClickEvent e) {
        if(e.getClickedInventory() == null) return;
        if(!(e.getWhoClicked() instanceof Player)) return;
        if(e.getClickedInventory() == e.getWhoClicked().getInventory()) return;
        if(e.getView().getTitle().equalsIgnoreCase("")) return;
        if(e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR)) return;

        for (GUI gui : this.guiList) {
            if(!TextUtils.formatColorCodes(gui.getTitle()).equalsIgnoreCase(TextUtils.formatColorCodes(e.getView().getTitle()))) continue;

            gui.onClick(e, (Player) e.getWhoClicked());
            break;
        }
    }
}
