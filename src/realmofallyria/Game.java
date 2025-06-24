
package realmofallyria;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Game extends javax.swing.JFrame {

    // --+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    // <editor-fold desc="variables">
    int textIndex = 0;
    int storylineIndex = 0;

    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="storyline /monologues manuscript">
    String[] INTRO_TUTORIAL = {
        "Welcome to the Realm of Allyria.",
        "Before your travels begin adventurer...",
        "Do you remember who is the kingdom's heir?"};
    String[] NAME_TUTORIAL = {
        "That is correct.",
        "Aside from that, do you remember your name?",
        "What shall I refer to you, adventurer?"};
    String[] CHOOSE_CLASS_TUTORIAL = {
        "Pleased to meet you, {PLAYER}",
        "When I found you unconcious and injured I found letter that detailed a task.",
        "You were holding it, bloodstained.",
        "In the letter it states a task to meet {PRINCESS}.",
        "Since you were holding it I assume that this task was yours.",
        "Meeting {PRINCESS} could give you more information about your past.",
        "However, I am affraid something terrible has occured to them.",
        "This may distress you but...",
        "{UNDERWORLDPRINCE} has recently launched another invasion with their demonic army.",
        "They are the Ruler of the Naetaleth, the land of the demons.",
        "The kingdom's forces are fighting back, but this war has plunged our lands to chaos.",
        "The demonic forces have infiltrated this land and they have even reached as far inland as near this village itself.",
        "More importantly, {UNDERWORLDPRINCE} has kidnapped {PRINCESS}.",
        "But enough about that for now.",
        "At this, I am more concerned about your health.",
        "So let us continue with your diagnosis.",
        "Do you remember anything about affinities?",
        "In this world, everyone is born with them.",
        "Affinities are blessings from the gods.",
        "Everyone can only have one affinity when they are born.",
        "It is the will of the gods that this is so.",
        "The gods are also referred to simply as the Pantheon.",
        "Depending on which affinity you have, you will receive a boost in your attributes for every level.",
        "Those who have an affinity to Sanitas will receive a boost in Health Points.",
        "Those who have an affinity to Virtus will receive a blessing in Strength Points.",
        "Those who have an affinity to Tutela will receive a boost in Defense Points.",
        "Those who have an affinity to Madeis will receive a boost in Intelligence Points.",
        "Those who have an affinity to Celeritas will receive a boost in Agility Points.",
        "If you can, recount your affinity."};
    String[] EDIT_ATTRIBUTES_TUTORIAL = {
        "Is that so.",
        "Wonderful.",
        "Now do you remember your own attributes?",
        "Aside from affinities, everyone has a status panel.",
        "Here you can access your attributes.",
        "Health Points or [HP] determine your vitality.",
        "Strength Points or [SP] determine your might.",
        "Defence Points or [DP] determine your fortitude.",
        "Intelligence Points or [IP] determine your magical might.",
        "Agility Points or [AP] determine your dexterity.",
        "For every level, your attribute points increase by one.",
        "Additionally, for every level you have 10 attributes to spend.",
        "It seems you are a level one.",
        "Do you think you can remember your status panel correctly?"};
    String[] CHOOSE_GEAR_TUTORIAL = {
        "The world outside of civilization is teeming with ravenous beasts.",
        "Especially nowadays, the world has become such a dangerous place.",
        "You will need something to defend yourself when you traverse the lands beyond civilization.",
        "It is not much by any standard.",
        "But these weapons are all we can spare",
        "Please choose one."};
    String[] BONUS_ARMOR_TUTORIAL = {
        "A weapon will not be enough.",
        "I will give you some protection.",
        "But remember never overstep yourself.",
        "Know your own strengths and weaknesses.",
        "I will spare you a leather armor.",};
    String[] CHOOSESKILL_TUTORIAL = {
        "As for the last part of this diagnosis, do you remember anything about skills?",
        "Some few have the ability to use skills.",
        "Skills consume Magic Power in order to be used.",
        "Once Magic Power is exhausted it may only be replenished through rest.",
        "These skills give an edge in combat.",
        "Some let you bend elemental forces to your will.",
        "Some alter your body itself.",
        "The nature of skills is still very unkown to even the best sages.",
        "That it is considered as magic.",
        "You to be one of the blessed few to be able to wield skills.",
        "It seems you know one skill.",
        "Do you think you can remember which it was?"};
    String[] VILLAGE_TUTORIAL = {
        "My diagnosis ends here.",
        "The healers have confirmed that you have recovered fully",
        "Though as for your memory, I have no other suggestions than going to meet {PRINCESS}.",
        "But that is easier said than done",
        "At this moment you may not be able to face strong foes.",
        "However, I sense potential in you.",
        "I believe your arrival in this village was not simply coincidence, but instead a blessing from the gods itself.",
        "If you believe in yourself then I will help you reach your potential.",
        "I am {VILLAGEELDER}.",
        "I will let you reside freely in a spare lodge here.",
        "So consider it your new home.",
        "With that said, get a feel of the world.",
        "Whenever you feel that you are ready, then come talk to me."};
    String[] LORE_TUTORIAL = {
        "Greetings, {PLAYER}.",
        "As you know, the world is in peril as of now.",
        "The kingdom's forces are engaged in a fierce war againts the demon army.",
        "That is why time is of the essence, so I will teach you quickly.",
        "Your current strength will not do you any good against even the weakest kind of demon.",
        "You will need to train.",
        "In order to face strong foes you must get stronger first.",
        "I captuted a slime from the grasslands.",
        "You will face it in combat.",
        "But before you begin, you need to learn a few things.",
        "Fistly, whoever has the higher agility points will attack first.",
        "You do not know their agility points so be careful.",
        "Lastly, the longer your battle lasts the more experience you gain.",
        "I do not expect you to die against this slime, but please do not surprise me.",
        "Defeat the slime and complete your training.",};
    String[] DEFEAT_TUTORIAL = {
        "You were defeated by a mere slime?",
        "After every battle you will receive a certain amount of XP and coins.",
        "It may not be as much as winning a battle.",
        "But at least you are growing stronger with every battle.",
        "Never give up, {PLAYER}."};
    String[] VICTORY_TUTORIAL = {
        "Good work on defeating that slime.",
        "After every battle you will receive a certain amount of XP and coins.",
        "For your victory you have received the complete package.",
        "Keep it up and keep becoming stronger.",
        "You have done excellently, {PLAYER}."};
    String[] END_TUTORIAL = {
        "Take this as a learning experience.",
        "View every as an opportunity to grow.",
        "I shall give you my blessing to explore the world and become stronger.",
        "But before you go I will patch you up to full health.",
        "Keep in mind that if you need healing rest in your home for a moment.",
        "Earn experience points and grow stronger.",
        "Moreover, acquire currency from defeating monsters.",
        "Visit the local travelling merchant if you wish to upgrade your gear.",
        "And if you are interested, then I have a quest for you.",
        "Your path to greatness has now just begun.",
        "Safe travels and may the Pantheon always be with you, {PLAYER}."};
    String[] SLIME_VILLAGE = {
        "Greetings, {PLAYER}.",
        "All great quests begin small.",
        "So for your first quest I have a request for you.",
        "The wilderness past the walls of this village is dangerous and riddled with monsters lurking about.",
        "These monsters instill fear within the hearts of my citizens.",
        "That's why I request you to rid these lands of these monsters.",
        "Not only will my citizens feel safer, you will be recognized as a hero soon enough",
        "Besides, I will pay accordingly.",
        "Your task will be to kill three slimes from the grasslands."};
    String[] GOBLIN_VILLAGE = {
        "Greetings, {PLAYER}.",
        "You have done excellently once again, {PLAYER}.",
        "As promised you will be compensated for your efforts.",
        "But as you have probably guessed, that first task was simply a test.",
        "And you passed it with flying colors.",
        "Now we have to escalate.",
        "You must grow stronger now that you have a true taste in combat.",
        "That is why you will continue your quest to rid the grasslands of such wretched monsters.",
        "In your adventures into the grasslands you may have encountered a few pecular green mongrels already.",
        "Goblins is what they're usually called.",
        "And pests is all they're worth.",
        "The grasslands will be better off without them.",
        "Kill two goblins from the grasslands."};
    String[] WOLF_VILLAGE = {
        "Greetings, {PLAYER}.",
        "I extend myself and my people's utmost gratitue to you for your previous work, {PLAYER}.",
        "Once again we ask something of you.",
        "Another threat arises in our peaceful settlement.",
        "Locals have sighted a lone wolf lurking around, often attacking livestock.",
        "And in some cases villagers.",
        "You may or may not have encountered it yourself in your travels to the grasslands.",
        "This lone wolf has become a pest for long enough.",
        "Rid this place of that troublesome monster."};
    String[] BARON_BOSS_VILLAGE = {
        "Greetings, {PLAYER}.",
        "As you should know, the demon army's reach has become far.",
        "Fortunately, we have found the lair of one of {UNDERWORLDPRINCE}'s underlings",
        "{BARON}.",
        "If you believe that you are strong enough to subjugate them, then please do rid the grasslands of their presence.",
        "My citizens and myself will be thankful for your help in this.",
        "Of course you will be paid bountifully.",
        "But please consider your strength before charging into that place.",
        "{BARON} will have minions and you will have to deal with them.",
        "These minions will be far stronger than anything you have faced in the wilderness.",
        "But more importantly, {BARON} themselves are in a whole other level.",
        "So please assess your strength before attacking the lair.",
        "May the gods bless you on this quest, {PLAYER}."};
    String[] BARON_BATTLE_VILLAGE = {
        "So you're the trouble maker invading my lair?",
        "I have heard of your efforts, {PLAYER}.",
        "Its entirely pathetic really.",
        "You people have always tried to resist our invasions time and time again.",
        "But this time we will not fail.",
        "All your efforts to save this doomed realm will be for nought.",
        "My master will come and annihilate everyone you know soon enough.",
        "Challenging me to battle is just as pathetic.",
        "I will crush you like the insect you are."};
    /*
    String[] CHOOSESKILL_TUTORIAL = {
        "Greetings, {PLAYER}.",
        "I extend mine and my citizen's biggest gratitude to you for ridding this place of {BARON}.",
        "We rejoice in the peace you have successfully secured for everyone.",
        "For as long as we live we shall commemorate your achievement in the service of my village.",
        "You have grown much since when we first met.",
        "I feel pride in your growing strength.",
        "However, you have not reached your full potential yet.",
        "There are harsher challenges to face.",
        "Stronger foes to subjugate.",
        "It is time for you to move to the next stage of your journey.",
        "My fellow in a town over has apparently sighted another one of {UNDERWORLDPRINCE}'s underlings.",
        "Unfortunately, I cannot give any more information than I know",
        "You must go to the town yourself to learn more.",
        "But before that I must give you something special in commemoration of your recent achievement.",
        "Of course you will still receive your promised rewards.",
        "But more than that I want to give you something no such coins can ever purchase.",
        "Only once in a few years has an adventurer such as yourself come so far and achieved so much.",
        "Which makes you a special case.",
        "This makes you eligible to receive something special.",
        "As you may have encountered in your travels.",
        "Some foes can use skills that give them an edge in battle.",
        "These abilities are called skills and they consume Magic Power in order to be used.",
        "Once Magic Power is exhausted it may only be replenished through rest.",
        "You my dear, {PLAYER} will now receive your first skill.",
        "You may choose one to your liking.",
        "Now choose your first skill wisely."};
     */
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------

    Player player;
    Hostile enemy;
    Battle battle;
    Dungeon dungeon;
    Quest quest;
    Store store;

    String currentLocation = "";
    String currentStore = "";

    // indicates whether the dialogue menu is opened.
    Random gameRandomizer = new Random();

    // character indexes
    // <editor-fold desc="character tags">
    /*
        character name tags:
        battlePlayer: {PLAYER}
        princess: {PRINCESS}

        ALLIES
        village elder: {VILLAGEELDER}
        lord/ lady: {LORD}
        duke/ duchess: {DUKE}
        commander: {COMMANDER}
        king/ queen: {KING}

        DEMONS
        baron/ baroness: {BARON}
        general:  {GENERAL}
        lesser lord/ lady: {LESSERLORD}
        arch demon: {ARCH}
        prince/ princess of the underworld: {UNDERWORLDPRINCE}
        
        gender tags:
        he/ she: {HE}
     */
    // </editor-fold>
    LinkedList<String> charactersMap = new LinkedList<String>() {

        {
            add("{PLAYER}");
            add("{PRINCESS}");
            add("{VILLAGEELDER}");
            add("{LORD}");
            add("{DUKE}");
            add("{COMMANDER}");
            add("{KING}");
            add("{BARON}");
            add("{GENERAL}");
            add("{LESSERLORD}");
            add("{ARCH}");
            add("{UNDERWORLDPRINCE}");
        }

    };

    // format: <character index, {"first name", "last name", "title", "gender"}>
    HashMap<Integer, String[]> characterNames = new HashMap<>();

    Boolean invadingBossLair = false;

    // fonts
    public static HashMap<String, Font> fontsMap = new HashMap<>();

    // revamped popup feature
    public static HashMap<String, WarningPopup> warningsMap = new HashMap<>();

    static boolean finishedLoadingText = true;
    static LinkedList<LoadingText> loadingInstances = new LinkedList<>();

    // stores the skills for the skill button
    Skill[] availableSkills = new Skill[3];

    // </editor-fold>
    // --+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
    public Game() {

        // debugging stuff (0 for normal)
        // 6 for testing dialogue menu
        // 7 for skipping tutorial
        //9 for testing shop
        // 12 for baron boss battle
        storylineIndex = 0;
        quest = new Quest();

        // -----------------------------------------------------------------------------------------------------------
        // <editor-fold desc="debugging/ QA testing stuff">
//        player = new Player();
//        characterNames.put(0, new String[]{"Meme Bashame", "", "Player", "m"});
//        player.typeAffinity = "Celeritas";
//        player.chooseAffinity(10);
//        player.name = "Meme Bashame";
//        player.typeAffinity = "Celeritas";
//        player.level = 1;
//
//        player.chooseAffinity(10);
//        player.confirmAttributeChanges();
//        Weapon debugWeapon = new Weapon("Simple Bow", 1, new Skill("Shoot"), 3, 0, 3);
//        Armor debugArmor = new Armor("Leather Armor", 1, 3, 3);
//        player.equipGear(debugWeapon, debugArmor);
//
////        player.attributePoints += 1000;
//        player.totalCoins += 10000;
//        player.currentHP = 1;
//
//        player.skill1 = new Skill("Heal");
//        player.skill2 = new Skill("Fireball");
//        player.skill3 = new Skill("True Strike");
        // </editor-fold>
        // -----------------------------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------------------------
        // <editor-fold desc="Register the custom fonts">
        try {

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font headerFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\realmofallyria\\fonts\\CloisterBlackLight.ttf")).deriveFont(18f);
            ge.registerFont(headerFont);
            Font bodyFont = Font.createFont(Font.TRUETYPE_FONT, new File("src\\realmofallyria\\fonts\\PlacidArmor.otf")).deriveFont(18f);
            ge.registerFont(bodyFont);
            fontsMap.put("Header", headerFont);
            fontsMap.put("Body", bodyFont);

        } catch (FontFormatException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // </editor-fold>
        // -----------------------------------------------------------------------------------------------------------
        initComponents();

        // -----------------------------------------------------------------------------------------------------------
        // <editor-fold desc="Set the background image in the intro screen according to size">
        ImageIcon ii = new ImageIcon(getClass().getResource("/realmofallyria/images/intro.png"));
        Image image = (ii).getImage().getScaledInstance(label_IntroBackground.getWidth(),
                label_IntroBackground.getHeight(), Image.SCALE_SMOOTH);
        ii = new ImageIcon(image);
        label_IntroBackground.setIcon(ii);
        // </editor-fold>
        // -----------------------------------------------------------------------------------------------------------

        // hidden for the intro sequence
        panel_PickRescue.setVisible(false);
        textField_NameField.setVisible(false);

        hideScreens();

        // enable these along with putting storylineIndex to 8 to skip tutorial
//        openGameScreen();
//        travelToLocation("Village");
//        panel_Intro.setVisible(false);
//        generateNPCNames(PrincessPrince.PRINCESS);
    }

    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="screen visibility & convert coin method stuff">
    private void openGameScreen() {

        if (player.attributePoints > 0) {

            button_Status.setText("Status (•)");

        } else {

            button_Status.setText("Status");

        }

        loadText(String.format("Health Points (HP): %.2f / %.2f\n", player.currentHP, player.maxHP),
                label_GameHP, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText(String.format("Magic Points (MP): %.2f / %.2f\n", player.currentMP, player.maxMP),
                label_GameMP, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText(String.format("Experience Points (XP): %.2f / %.2f\n", player.xp, player.xpNeeded),
                label_GameXP, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText(String.format("Coins: %s", convertCoins(player.totalCoins)),
                label_GameCurrency, false, true, TextLoadingSpeeds.NORMAL.textSpeed);

        loadText("Inventory", button_Inventory, false, true, TextLoadingSpeeds.SLOW.textSpeed);
        loadText("Travel", button_Travel, false, true, TextLoadingSpeeds.SLOW.textSpeed);

        hideScreens();

        panel_Game.setVisible(true);
    }

    private void hideScreens() {
        textField_NameField.setVisible(false);
        panel_AffinitiesMenu.setVisible(false);
        panel_Status.setVisible(false);
        panel_StartingGear.setVisible(false);
        panel_Game.setVisible(false);
        panel_Inventory.setVisible(false);
        panel_Travel.setVisible(false);
        button_Return.setVisible(false);
        panel_Home.setVisible(false);
        panel_Storyline.setVisible(false);
        panel_Combat.setVisible(false);
        panel_Dungeon.setVisible(false);
        panel_Quest.setVisible(false);
        panel_Shop.setVisible(false);
        panel_ChooseSkill.setVisible(false);
        panel_Skills.setVisible(false);

        // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
        // does not belong here
        // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
        button_Forest.setVisible(false);
        button_Town.setVisible(false);
        button_City.setVisible(false);
        button_Dungeon.setVisible(false);
    }

    public String convertCoins(int givenCoins) {

        int goldCoins = (int) givenCoins / 2500;
        int remainingCoins = (int) givenCoins % 2500;
        int silverCoins = remainingCoins / 50;
        int copperCoins = remainingCoins % 50;

//        System.out.println("givenCoins: " + givenCoins);
//        System.out.println("goldCoins: " + goldCoins);
//        System.out.println("remainingCoins: " + remainingCoins);
//        System.out.println("silverCoins: " + silverCoins);
//        System.out.println("copperCoins: " + copperCoins);
//        System.out.println();
        return String.format("%s%s%s",
                copperCoins > 0 ? String.format("%s [Copper] ", copperCoins) : "",
                silverCoins > 0 ? String.format(" %s [Silver] ", silverCoins) : "",
                goldCoins > 0 ? String.format(" %s [Gold] ", goldCoins) : "");

    }

    private void showReturn() {

        button_Return.setVisible(true);
        loadText("Return",
                button_Return, false, true, TextLoadingSpeeds.NORMAL.textSpeed);

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_Main = new javax.swing.JPanel();
        button_Return = new javax.swing.JButton();
        label_Header = new javax.swing.JLabel();
        panel_Home = new javax.swing.JPanel();
        label_HomeLabel = new javax.swing.JLabel();
        label_HomeReturn = new javax.swing.JLabel();
        panel_Travel = new javax.swing.JPanel();
        button_Village = new javax.swing.JButton();
        button_Grasslands = new javax.swing.JButton();
        button_Town = new javax.swing.JButton();
        button_Forest = new javax.swing.JButton();
        button_City = new javax.swing.JButton();
        button_Dungeon = new javax.swing.JButton();
        label_Wilderness = new javax.swing.JLabel();
        label_Civilization = new javax.swing.JLabel();
        panel_Status = new javax.swing.JPanel();
        panel_AttributesActions = new javax.swing.JPanel();
        button_AttributesConfirm = new javax.swing.JButton();
        button_AttributesReset = new javax.swing.JButton();
        label_PlayerName = new javax.swing.JLabel();
        label_PlayerAffinity = new javax.swing.JLabel();
        label_Level = new javax.swing.JLabel();
        label_AvailablePoints = new javax.swing.JLabel();
        label_HealthPoints = new javax.swing.JLabel();
        label_StrengthPoints = new javax.swing.JLabel();
        label_DefensePoints = new javax.swing.JLabel();
        label_IntelligencePoints = new javax.swing.JLabel();
        label_AgilityPoints = new javax.swing.JLabel();
        panel_Dashes1 = new javax.swing.JPanel();
        label_Dash6 = new javax.swing.JLabel();
        label_Dash7 = new javax.swing.JLabel();
        label_Dash8 = new javax.swing.JLabel();
        label_Dash9 = new javax.swing.JLabel();
        label_Dash10 = new javax.swing.JLabel();
        panel_Dashes = new javax.swing.JPanel();
        label_Dash1 = new javax.swing.JLabel();
        label_Dash2 = new javax.swing.JLabel();
        label_Dash3 = new javax.swing.JLabel();
        label_Dash4 = new javax.swing.JLabel();
        label_Dash5 = new javax.swing.JLabel();
        panel_GearAddition = new javax.swing.JPanel();
        label_GearHP = new javax.swing.JLabel();
        label_GearSP = new javax.swing.JLabel();
        label_GearDP = new javax.swing.JLabel();
        label_GearIP = new javax.swing.JLabel();
        label_GearAP = new javax.swing.JLabel();
        panel_AttributesAddition = new javax.swing.JPanel();
        label_HPAddition = new javax.swing.JLabel();
        label_SPAddition = new javax.swing.JLabel();
        label_DPAddition = new javax.swing.JLabel();
        label_IPAddition = new javax.swing.JLabel();
        label_APAddition = new javax.swing.JLabel();
        panel_Total = new javax.swing.JPanel();
        label_TotalHP = new javax.swing.JLabel();
        label_TotalSP = new javax.swing.JLabel();
        label_TotalDP = new javax.swing.JLabel();
        label_TotalIP = new javax.swing.JLabel();
        label_TotalAP = new javax.swing.JLabel();
        panel_AttributesAdditionButtons = new javax.swing.JPanel();
        button_HPAddition = new javax.swing.JButton();
        button_SPAddition = new javax.swing.JButton();
        button_DPAddition = new javax.swing.JButton();
        button_IPAddition = new javax.swing.JButton();
        button_APAddition = new javax.swing.JButton();
        panel_Inventory = new javax.swing.JPanel();
        label_Armor = new javax.swing.JLabel();
        label_PDef = new javax.swing.JLabel();
        label_MDef = new javax.swing.JLabel();
        label_Weapon = new javax.swing.JLabel();
        label_PDmg = new javax.swing.JLabel();
        label_MDmg = new javax.swing.JLabel();
        label_CC = new javax.swing.JLabel();
        panel_SubTotal = new javax.swing.JPanel();
        label_TotalPDef = new javax.swing.JLabel();
        label_TotalMDef = new javax.swing.JLabel();
        label_TotalCC = new javax.swing.JLabel();
        label_TotalMDmg = new javax.swing.JLabel();
        label_TotalPDmg = new javax.swing.JLabel();
        panel_Dashes2 = new javax.swing.JPanel();
        label_Dash15 = new javax.swing.JLabel();
        label_Dash14 = new javax.swing.JLabel();
        label_Dash13 = new javax.swing.JLabel();
        label_Dash12 = new javax.swing.JLabel();
        label_Dash11 = new javax.swing.JLabel();
        panel_SubGearAddition = new javax.swing.JPanel();
        label_GearPDef = new javax.swing.JLabel();
        label_GearMDef = new javax.swing.JLabel();
        label_GearPDmg = new javax.swing.JLabel();
        label_GearMDmg = new javax.swing.JLabel();
        label_GearCC = new javax.swing.JLabel();
        button_Skills = new javax.swing.JButton();
        panel_Skills = new javax.swing.JPanel();
        label_BasicAttackName = new javax.swing.JLabel();
        label_Skill1Name = new javax.swing.JLabel();
        label_Skill2Name = new javax.swing.JLabel();
        label_Skill3Name = new javax.swing.JLabel();
        label_BasicAttackLevel = new javax.swing.JLabel();
        label_Skill1Level = new javax.swing.JLabel();
        label_Skill2Level = new javax.swing.JLabel();
        label_Skill3Level = new javax.swing.JLabel();
        label_BasicAttackDmg = new javax.swing.JLabel();
        label_Skill1Dmg = new javax.swing.JLabel();
        label_Skill2Dmg = new javax.swing.JLabel();
        label_Skill3Dmg = new javax.swing.JLabel();
        label_BasicAttackCost = new javax.swing.JLabel();
        label_Skill1Cost = new javax.swing.JLabel();
        label_Skill2Cost = new javax.swing.JLabel();
        label_Skill3Cost = new javax.swing.JLabel();
        label_BasicAttackInflict = new javax.swing.JLabel();
        label_Skill1Inflict = new javax.swing.JLabel();
        label_Skill2Inflict = new javax.swing.JLabel();
        label_Skill3Inflict = new javax.swing.JLabel();
        label_BasicAttackDescription = new javax.swing.JLabel();
        label_Skill1Description = new javax.swing.JLabel();
        label_Skill2Description = new javax.swing.JLabel();
        label_Skill3Description = new javax.swing.JLabel();
        panel_Game = new javax.swing.JPanel();
        button_Quest = new javax.swing.JButton();
        button_Travel = new javax.swing.JButton();
        button_Inventory = new javax.swing.JButton();
        button_Status = new javax.swing.JButton();
        panel_PlayerStats = new javax.swing.JPanel();
        label_GameCurrency = new javax.swing.JLabel();
        label_GameXP = new javax.swing.JLabel();
        label_GameMP = new javax.swing.JLabel();
        label_GameHP = new javax.swing.JLabel();
        label_Location = new javax.swing.JLabel();
        panel_LocationPanel = new javax.swing.JPanel();
        button_Place1 = new javax.swing.JButton();
        button_Place2 = new javax.swing.JButton();
        button_Place3 = new javax.swing.JButton();
        label_GameBackground = new javax.swing.JLabel();
        panel_Quest = new javax.swing.JPanel();
        label_QuestTitle = new javax.swing.JLabel();
        label_QuestBody = new javax.swing.JLabel();
        label_QuestReward = new javax.swing.JLabel();
        label_QuestCompleted = new javax.swing.JLabel();
        panel_Storyline = new javax.swing.JPanel();
        panel_PickRescue = new javax.swing.JPanel();
        button_Princess = new javax.swing.JButton();
        button_Prince = new javax.swing.JButton();
        textField_NameField = new javax.swing.JTextField();
        label_Title = new javax.swing.JLabel();
        label_Talker = new javax.swing.JLabel();
        label_StorylineText = new javax.swing.JLabel();
        button_Confirm = new javax.swing.JButton();
        panel_StartingGear = new javax.swing.JPanel();
        button_IronSword = new javax.swing.JButton();
        label_IronSword = new javax.swing.JLabel();
        button_SimpleBow = new javax.swing.JButton();
        label_SimpleBow = new javax.swing.JLabel();
        button_CrudeWand = new javax.swing.JButton();
        label_CrudeWand = new javax.swing.JLabel();
        panel_AffinitiesMenu = new javax.swing.JPanel();
        button_Sanitas = new javax.swing.JButton();
        label_Sanitas = new javax.swing.JLabel();
        button_Virtus = new javax.swing.JButton();
        label_Virtus = new javax.swing.JLabel();
        button_Tutela = new javax.swing.JButton();
        label_Tutela = new javax.swing.JLabel();
        button_Madeis = new javax.swing.JButton();
        label_Madeis = new javax.swing.JLabel();
        button_Celeritas = new javax.swing.JButton();
        label_Celeritas = new javax.swing.JLabel();
        panel_Shop = new javax.swing.JPanel();
        label_ShopLocation = new javax.swing.JLabel();
        label_ShopCurrency = new javax.swing.JLabel();
        button_BuySword = new javax.swing.JButton();
        button_BuyBow = new javax.swing.JButton();
        button_BuyWand = new javax.swing.JButton();
        button_BuyArmor = new javax.swing.JButton();
        button_EquipSword = new javax.swing.JButton();
        button_EquipBow = new javax.swing.JButton();
        button_EquipWand = new javax.swing.JButton();
        button_EquipArmor = new javax.swing.JButton();
        panel_ChooseSkill = new javax.swing.JPanel();
        button_ChooseSkill1 = new javax.swing.JButton();
        label_ChooseSkill1 = new javax.swing.JLabel();
        button_ChooseSkill2 = new javax.swing.JButton();
        label_ChooseSkill2 = new javax.swing.JLabel();
        button_ChooseSkill3 = new javax.swing.JButton();
        label_ChooseSkill3 = new javax.swing.JLabel();
        panel_Combat = new javax.swing.JPanel();
        label_CombatPlayer = new javax.swing.JLabel();
        label_CombatPlayerAffinity = new javax.swing.JLabel();
        label_CombatHP = new javax.swing.JLabel();
        label_CombatMP = new javax.swing.JLabel();
        label_CombatEnemy = new javax.swing.JLabel();
        label_CombatEnemyAffinity = new javax.swing.JLabel();
        label_EnemyHP = new javax.swing.JLabel();
        label_EnemyMP = new javax.swing.JLabel();
        button_FleeCombat = new javax.swing.JButton();
        button_UseSkill = new javax.swing.JButton();
        panel_CombatSkills = new javax.swing.JPanel();
        button_UseBasicAttack = new javax.swing.JButton();
        button_UseSkill1 = new javax.swing.JButton();
        button_UseSkill2 = new javax.swing.JButton();
        button_UseSkill3 = new javax.swing.JButton();
        panel_CombatLog = new javax.swing.JPanel();
        label_CombatLog = new javax.swing.JLabel();
        panel_Dungeon = new javax.swing.JPanel();
        label_DungeonLocation = new javax.swing.JLabel();
        label_EncounterLog = new javax.swing.JLabel();
        button_DungeonAttack = new javax.swing.JButton();
        button_DungeonFlee = new javax.swing.JButton();
        button_DungeonReturn = new javax.swing.JButton();
        label_WildernessBackground = new javax.swing.JLabel();
        panel_Intro = new javax.swing.JPanel();
        label_IntroHeader = new javax.swing.JLabel();
        label_IntroBody = new javax.swing.JLabel();
        label_IntroBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 500));
        setResizable(false);

        panel_Main.setBackground(new java.awt.Color(25, 25, 33));
        panel_Main.setOpaque(false);
        panel_Main.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_MainMouseClicked(evt);
            }
        });
        panel_Main.setLayout(null);

        button_Return.setBackground(new java.awt.Color(67, 67, 79));
        button_Return.setFont(fontsMap.get("Body").deriveFont(14f));
        button_Return.setForeground(new java.awt.Color(242, 242, 242));
        button_Return.setText("Return");
        button_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ReturnActionPerformed(evt);
            }
        });
        panel_Main.add(button_Return);
        button_Return.setBounds(450, 73, 140, 30);

        label_Header.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label_Header.setForeground(new java.awt.Color(25, 25, 33));
        label_Header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Header.setToolTipText("");
        panel_Main.add(label_Header);
        label_Header.setBounds(6, 6, 590, 47);
        label_Header.setFont(fontsMap.get("Body").deriveFont(14f));

        panel_Home.setBackground(new java.awt.Color(25, 25, 33));
        panel_Home.setLayout(null);

        label_HomeLabel.setFont(fontsMap.get("Body"));
        label_HomeLabel.setForeground(new java.awt.Color(242, 242, 242));
        label_HomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_HomeLabel.setText("Restore full HP and MP");
        panel_Home.add(label_HomeLabel);
        label_HomeLabel.setBounds(10, 70, 570, 270);

        label_HomeReturn.setFont(fontsMap.get("Body"));
        label_HomeReturn.setForeground(new java.awt.Color(242, 242, 242));
        label_HomeReturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_HomeReturn.setText("Restore full HP and MP");
        panel_Home.add(label_HomeReturn);
        label_HomeReturn.setBounds(10, 350, 570, 60);

        panel_Main.add(panel_Home);
        panel_Home.setBounds(5, 60, 590, 440);
        panel_Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_HomeMouseClicked(evt);
            }
        });

        panel_Travel.setBackground(new java.awt.Color(25, 25, 33));
        panel_Travel.setLayout(null);

        button_Village.setBackground(new java.awt.Color(67, 67, 79));
        button_Village.setFont(fontsMap.get("Header").deriveFont(24f));
        button_Village.setForeground(new java.awt.Color(242, 242, 242));
        button_Village.setText("Village");
        button_Village.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_VillageActionPerformed(evt);
            }
        });
        panel_Travel.add(button_Village);
        button_Village.setBounds(30, 120, 240, 90);

        button_Grasslands.setBackground(new java.awt.Color(67, 67, 79));
        button_Grasslands.setFont(fontsMap.get("Header").deriveFont(24f));
        button_Grasslands.setForeground(new java.awt.Color(242, 242, 242));
        button_Grasslands.setText("Grasslands");
        button_Grasslands.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_GrasslandsActionPerformed(evt);
            }
        });
        panel_Travel.add(button_Grasslands);
        button_Grasslands.setBounds(310, 120, 240, 90);

        button_Town.setBackground(new java.awt.Color(67, 67, 79));
        button_Town.setFont(fontsMap.get("Header").deriveFont(24f));
        button_Town.setForeground(new java.awt.Color(242, 242, 242));
        button_Town.setText("Fortress");
        button_Town.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TownActionPerformed(evt);
            }
        });
        panel_Travel.add(button_Town);
        button_Town.setBounds(30, 220, 240, 90);

        button_Forest.setBackground(new java.awt.Color(67, 67, 79));
        button_Forest.setFont(fontsMap.get("Header").deriveFont(24f));
        button_Forest.setForeground(new java.awt.Color(242, 242, 242));
        button_Forest.setText("Forest");
        button_Forest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ForestActionPerformed(evt);
            }
        });
        panel_Travel.add(button_Forest);
        button_Forest.setBounds(310, 220, 240, 90);

        button_City.setBackground(new java.awt.Color(67, 67, 79));
        button_City.setFont(fontsMap.get("Header").deriveFont(24f));
        button_City.setForeground(new java.awt.Color(242, 242, 242));
        button_City.setText("Capital City");
        button_City.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CityActionPerformed(evt);
            }
        });
        panel_Travel.add(button_City);
        button_City.setBounds(30, 320, 240, 90);

        button_Dungeon.setBackground(new java.awt.Color(67, 67, 79));
        button_Dungeon.setFont(fontsMap.get("Header").deriveFont(24f));
        button_Dungeon.setForeground(new java.awt.Color(242, 242, 242));
        button_Dungeon.setText("Dungeon");
        button_Dungeon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DungeonActionPerformed(evt);
            }
        });
        panel_Travel.add(button_Dungeon);
        button_Dungeon.setBounds(310, 320, 240, 90);

        label_Wilderness.setFont(fontsMap.get("Header").deriveFont(32f));
        label_Wilderness.setForeground(new java.awt.Color(242, 242, 242));
        label_Wilderness.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Wilderness.setText("Wilderness");
        panel_Travel.add(label_Wilderness);
        label_Wilderness.setBounds(310, 50, 240, 60);

        label_Civilization.setFont(fontsMap.get("Header").deriveFont(32f));
        label_Civilization.setForeground(new java.awt.Color(242, 242, 242));
        label_Civilization.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Civilization.setText("Civilization");
        panel_Travel.add(label_Civilization);
        label_Civilization.setBounds(30, 50, 240, 60);

        panel_Main.add(panel_Travel);
        panel_Travel.setBounds(5, 60, 590, 440);

        panel_Status.setBackground(new java.awt.Color(25, 25, 33));
        panel_Status.setLayout(null);

        panel_AttributesActions.setOpaque(false);
        panel_AttributesActions.setLayout(null);

        button_AttributesConfirm.setBackground(new java.awt.Color(67, 67, 79));
        button_AttributesConfirm.setFont(fontsMap.get("Body").deriveFont(14f));
        button_AttributesConfirm.setForeground(new java.awt.Color(242, 242, 242));
        button_AttributesConfirm.setText("<html>Confirm");
        button_AttributesConfirm.setToolTipText("");
        button_AttributesConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AttributesConfirmActionPerformed(evt);
            }
        });
        panel_AttributesActions.add(button_AttributesConfirm);
        button_AttributesConfirm.setBounds(140, 0, 130, 40);

        button_AttributesReset.setBackground(new java.awt.Color(67, 67, 79));
        button_AttributesReset.setFont(fontsMap.get("Body").deriveFont(14f));
        button_AttributesReset.setForeground(new java.awt.Color(242, 242, 242));
        button_AttributesReset.setText("<html>Reset");
        button_AttributesReset.setToolTipText("");
        button_AttributesReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_AttributesResetActionPerformed(evt);
            }
        });
        panel_AttributesActions.add(button_AttributesReset);
        button_AttributesReset.setBounds(0, 0, 130, 40);

        panel_Status.add(panel_AttributesActions);
        panel_AttributesActions.setBounds(300, 390, 270, 40);

        label_PlayerName.setFont(fontsMap.get("Body"));
        label_PlayerName.setForeground(new java.awt.Color(242, 242, 242));
        label_PlayerName.setText("Adventurer");
        panel_Status.add(label_PlayerName);
        label_PlayerName.setBounds(10, 10, 390, 30);

        label_PlayerAffinity.setFont(fontsMap.get("Body"));
        label_PlayerAffinity.setForeground(new java.awt.Color(242, 242, 242));
        label_PlayerAffinity.setText("Affinity");
        panel_Status.add(label_PlayerAffinity);
        label_PlayerAffinity.setBounds(10, 40, 390, 30);

        label_Level.setFont(fontsMap.get("Body"));
        label_Level.setForeground(new java.awt.Color(242, 242, 242));
        label_Level.setText("LVL: 0");
        panel_Status.add(label_Level);
        label_Level.setBounds(10, 70, 390, 30);

        label_AvailablePoints.setFont(fontsMap.get("Body"));
        label_AvailablePoints.setForeground(new java.awt.Color(242, 242, 242));
        label_AvailablePoints.setText("Available Points: 0");
        panel_Status.add(label_AvailablePoints);
        label_AvailablePoints.setBounds(10, 100, 390, 30);

        label_HealthPoints.setFont(fontsMap.get("Body").deriveFont(14f));
        label_HealthPoints.setForeground(new java.awt.Color(242, 242, 242));
        label_HealthPoints.setText("Health Points:");
        panel_Status.add(label_HealthPoints);
        label_HealthPoints.setBounds(10, 140, 190, 30);

        label_StrengthPoints.setFont(fontsMap.get("Body").deriveFont(14f));
        label_StrengthPoints.setForeground(new java.awt.Color(242, 242, 242));
        label_StrengthPoints.setText("Strength Points:");
        panel_Status.add(label_StrengthPoints);
        label_StrengthPoints.setBounds(10, 190, 190, 30);

        label_DefensePoints.setFont(fontsMap.get("Body").deriveFont(14f));
        label_DefensePoints.setForeground(new java.awt.Color(242, 242, 242));
        label_DefensePoints.setText("Defense Points:");
        panel_Status.add(label_DefensePoints);
        label_DefensePoints.setBounds(10, 240, 190, 30);

        label_IntelligencePoints.setFont(fontsMap.get("Body").deriveFont(14f));
        label_IntelligencePoints.setForeground(new java.awt.Color(242, 242, 242));
        label_IntelligencePoints.setText("Intelligence Points:");
        panel_Status.add(label_IntelligencePoints);
        label_IntelligencePoints.setBounds(10, 290, 190, 30);

        label_AgilityPoints.setFont(fontsMap.get("Body").deriveFont(14f));
        label_AgilityPoints.setForeground(new java.awt.Color(242, 242, 242));
        label_AgilityPoints.setText("Agility Points:");
        panel_Status.add(label_AgilityPoints);
        label_AgilityPoints.setBounds(10, 340, 190, 30);

        panel_Dashes1.setOpaque(false);
        panel_Dashes1.setLayout(null);

        label_Dash6.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash6.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash6.setText("-");
        panel_Dashes1.add(label_Dash6);
        label_Dash6.setBounds(0, 0, 30, 30);

        label_Dash7.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash7.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash7.setText("-");
        panel_Dashes1.add(label_Dash7);
        label_Dash7.setBounds(0, 50, 30, 30);

        label_Dash8.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash8.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash8.setText("-");
        panel_Dashes1.add(label_Dash8);
        label_Dash8.setBounds(0, 100, 30, 30);

        label_Dash9.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash9.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash9.setText("-");
        panel_Dashes1.add(label_Dash9);
        label_Dash9.setBounds(0, 150, 30, 30);

        label_Dash10.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash10.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash10.setText("-");
        panel_Dashes1.add(label_Dash10);
        label_Dash10.setBounds(0, 200, 30, 30);

        panel_Status.add(panel_Dashes1);
        panel_Dashes1.setBounds(390, 140, 30, 230);

        panel_Dashes.setOpaque(false);
        panel_Dashes.setLayout(null);

        label_Dash1.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash1.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash1.setText("-");
        panel_Dashes.add(label_Dash1);
        label_Dash1.setBounds(0, 0, 30, 30);

        label_Dash2.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash2.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash2.setText("-");
        panel_Dashes.add(label_Dash2);
        label_Dash2.setBounds(0, 50, 30, 30);

        label_Dash3.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash3.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash3.setText("-");
        panel_Dashes.add(label_Dash3);
        label_Dash3.setBounds(0, 100, 30, 30);

        label_Dash4.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash4.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash4.setText("-");
        panel_Dashes.add(label_Dash4);
        label_Dash4.setBounds(0, 150, 30, 30);

        label_Dash5.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash5.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash5.setText("-");
        panel_Dashes.add(label_Dash5);
        label_Dash5.setBounds(0, 200, 30, 30);

        panel_Status.add(panel_Dashes);
        panel_Dashes.setBounds(280, 140, 30, 230);

        panel_GearAddition.setOpaque(false);
        panel_GearAddition.setLayout(null);

        label_GearHP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_GearHP.setForeground(new java.awt.Color(242, 242, 242));
        label_GearHP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearHP.setText("(+0)");
        panel_GearAddition.add(label_GearHP);
        label_GearHP.setBounds(0, 0, 80, 30);

        label_GearSP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_GearSP.setForeground(new java.awt.Color(242, 242, 242));
        label_GearSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearSP.setText("(+0)");
        panel_GearAddition.add(label_GearSP);
        label_GearSP.setBounds(0, 50, 80, 30);

        label_GearDP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_GearDP.setForeground(new java.awt.Color(242, 242, 242));
        label_GearDP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearDP.setText("(+0)");
        panel_GearAddition.add(label_GearDP);
        label_GearDP.setBounds(0, 100, 80, 30);

        label_GearIP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_GearIP.setForeground(new java.awt.Color(242, 242, 242));
        label_GearIP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearIP.setText("(+0)");
        panel_GearAddition.add(label_GearIP);
        label_GearIP.setBounds(0, 150, 80, 30);

        label_GearAP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_GearAP.setForeground(new java.awt.Color(242, 242, 242));
        label_GearAP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearAP.setText("(+0)");
        panel_GearAddition.add(label_GearAP);
        label_GearAP.setBounds(0, 200, 80, 30);

        panel_Status.add(panel_GearAddition);
        panel_GearAddition.setBounds(310, 140, 80, 230);

        panel_AttributesAddition.setOpaque(false);
        panel_AttributesAddition.setLayout(null);

        label_HPAddition.setFont(fontsMap.get("Body").deriveFont(14f));
        label_HPAddition.setForeground(new java.awt.Color(242, 242, 242));
        label_HPAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_HPAddition.setText("(+0)");
        panel_AttributesAddition.add(label_HPAddition);
        label_HPAddition.setBounds(0, 0, 80, 30);

        label_SPAddition.setFont(fontsMap.get("Body").deriveFont(14f));
        label_SPAddition.setForeground(new java.awt.Color(242, 242, 242));
        label_SPAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_SPAddition.setText("(+0)");
        panel_AttributesAddition.add(label_SPAddition);
        label_SPAddition.setBounds(0, 50, 80, 30);

        label_DPAddition.setFont(fontsMap.get("Body").deriveFont(14f));
        label_DPAddition.setForeground(new java.awt.Color(242, 242, 242));
        label_DPAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_DPAddition.setText("(+0)");
        panel_AttributesAddition.add(label_DPAddition);
        label_DPAddition.setBounds(0, 100, 80, 30);

        label_IPAddition.setFont(fontsMap.get("Body").deriveFont(14f));
        label_IPAddition.setForeground(new java.awt.Color(242, 242, 242));
        label_IPAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_IPAddition.setText("(+0)");
        panel_AttributesAddition.add(label_IPAddition);
        label_IPAddition.setBounds(0, 150, 80, 30);

        label_APAddition.setFont(fontsMap.get("Body").deriveFont(14f));
        label_APAddition.setForeground(new java.awt.Color(242, 242, 242));
        label_APAddition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_APAddition.setText("(+0)");
        panel_AttributesAddition.add(label_APAddition);
        label_APAddition.setBounds(0, 200, 80, 30);

        panel_Status.add(panel_AttributesAddition);
        panel_AttributesAddition.setBounds(420, 140, 80, 230);

        panel_Total.setOpaque(false);
        panel_Total.setLayout(null);

        label_TotalHP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_TotalHP.setForeground(new java.awt.Color(242, 242, 242));
        label_TotalHP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalHP.setText("0");
        panel_Total.add(label_TotalHP);
        label_TotalHP.setBounds(0, 0, 80, 30);

        label_TotalSP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_TotalSP.setForeground(new java.awt.Color(242, 242, 242));
        label_TotalSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalSP.setText("0");
        panel_Total.add(label_TotalSP);
        label_TotalSP.setBounds(0, 50, 80, 30);

        label_TotalDP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_TotalDP.setForeground(new java.awt.Color(242, 242, 242));
        label_TotalDP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalDP.setText("0");
        panel_Total.add(label_TotalDP);
        label_TotalDP.setBounds(0, 100, 80, 30);

        label_TotalIP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_TotalIP.setForeground(new java.awt.Color(242, 242, 242));
        label_TotalIP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalIP.setText("0");
        panel_Total.add(label_TotalIP);
        label_TotalIP.setBounds(0, 150, 80, 30);

        label_TotalAP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_TotalAP.setForeground(new java.awt.Color(242, 242, 242));
        label_TotalAP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalAP.setText("0");
        panel_Total.add(label_TotalAP);
        label_TotalAP.setBounds(0, 200, 80, 30);

        panel_Status.add(panel_Total);
        panel_Total.setBounds(200, 140, 80, 230);

        panel_AttributesAdditionButtons.setOpaque(false);
        panel_AttributesAdditionButtons.setLayout(null);

        button_HPAddition.setBackground(new java.awt.Color(67, 67, 79));
        button_HPAddition.setFont(fontsMap.get("Body").deriveFont(14f));
        button_HPAddition.setForeground(new java.awt.Color(242, 242, 242));
        button_HPAddition.setText("<html>+");
        button_HPAddition.setToolTipText("");
        button_HPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_HPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_HPAddition);
        button_HPAddition.setBounds(0, 0, 80, 30);

        button_SPAddition.setBackground(new java.awt.Color(67, 67, 79));
        button_SPAddition.setFont(fontsMap.get("Body").deriveFont(14f));
        button_SPAddition.setForeground(new java.awt.Color(242, 242, 242));
        button_SPAddition.setText("<html>+");
        button_SPAddition.setToolTipText("");
        button_SPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_SPAddition);
        button_SPAddition.setBounds(0, 50, 80, 30);

        button_DPAddition.setBackground(new java.awt.Color(67, 67, 79));
        button_DPAddition.setFont(fontsMap.get("Body").deriveFont(14f));
        button_DPAddition.setForeground(new java.awt.Color(242, 242, 242));
        button_DPAddition.setText("<html>+");
        button_DPAddition.setToolTipText("");
        button_DPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_DPAddition);
        button_DPAddition.setBounds(0, 100, 80, 30);

        button_IPAddition.setBackground(new java.awt.Color(67, 67, 79));
        button_IPAddition.setFont(fontsMap.get("Body").deriveFont(14f));
        button_IPAddition.setForeground(new java.awt.Color(242, 242, 242));
        button_IPAddition.setText("<html>+");
        button_IPAddition.setToolTipText("");
        button_IPAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_IPAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_IPAddition);
        button_IPAddition.setBounds(0, 150, 80, 30);

        button_APAddition.setBackground(new java.awt.Color(67, 67, 79));
        button_APAddition.setFont(fontsMap.get("Body").deriveFont(14f));
        button_APAddition.setForeground(new java.awt.Color(242, 242, 242));
        button_APAddition.setText("<html>+");
        button_APAddition.setToolTipText("");
        button_APAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_APAdditionAdditionActionPerformed(evt);
            }
        });
        panel_AttributesAdditionButtons.add(button_APAddition);
        button_APAddition.setBounds(0, 200, 80, 30);

        panel_Status.add(panel_AttributesAdditionButtons);
        panel_AttributesAdditionButtons.setBounds(500, 140, 80, 230);

        panel_Main.add(panel_Status);
        panel_Status.setBounds(5, 60, 590, 440);

        panel_Inventory.setBackground(new java.awt.Color(25, 25, 33));
        panel_Inventory.setLayout(null);

        label_Armor.setFont(fontsMap.get("Body"));
        label_Armor.setForeground(new java.awt.Color(242, 242, 242));
        label_Armor.setText("Equipped Armor: ");
        panel_Inventory.add(label_Armor);
        label_Armor.setBounds(10, 10, 430, 50);

        label_PDef.setFont(fontsMap.get("Body").deriveFont(14f));
        label_PDef.setForeground(new java.awt.Color(242, 242, 242));
        label_PDef.setText("Physical Defence (PDef): ");
        panel_Inventory.add(label_PDef);
        label_PDef.setBounds(10, 80, 290, 30);

        label_MDef.setFont(fontsMap.get("Body").deriveFont(14f));
        label_MDef.setForeground(new java.awt.Color(242, 242, 242));
        label_MDef.setText("Magical Defense (MDef): ");
        panel_Inventory.add(label_MDef);
        label_MDef.setBounds(10, 130, 290, 30);

        label_Weapon.setFont(fontsMap.get("Body"));
        label_Weapon.setForeground(new java.awt.Color(242, 242, 242));
        label_Weapon.setText("Equipped Weapon: ");
        panel_Inventory.add(label_Weapon);
        label_Weapon.setBounds(10, 180, 430, 50);

        label_PDmg.setFont(fontsMap.get("Body").deriveFont(14f));
        label_PDmg.setForeground(new java.awt.Color(242, 242, 242));
        label_PDmg.setText("Physical Damage (PDmg): ");
        panel_Inventory.add(label_PDmg);
        label_PDmg.setBounds(10, 250, 290, 30);

        label_MDmg.setFont(fontsMap.get("Body").deriveFont(14f));
        label_MDmg.setForeground(new java.awt.Color(242, 242, 242));
        label_MDmg.setText("Magical Damage (MDmg): ");
        panel_Inventory.add(label_MDmg);
        label_MDmg.setBounds(10, 300, 290, 30);

        label_CC.setFont(fontsMap.get("Body").deriveFont(14f));
        label_CC.setForeground(new java.awt.Color(242, 242, 242));
        label_CC.setText("Critical Chance (CC): ");
        panel_Inventory.add(label_CC);
        label_CC.setBounds(10, 350, 290, 30);

        panel_SubTotal.setOpaque(false);
        panel_SubTotal.setLayout(null);

        label_TotalPDef.setFont(fontsMap.get("Body").deriveFont(14f));
        label_TotalPDef.setForeground(new java.awt.Color(242, 242, 242));
        label_TotalPDef.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalPDef.setText("0");
        panel_SubTotal.add(label_TotalPDef);
        label_TotalPDef.setBounds(0, 0, 80, 30);

        label_TotalMDef.setFont(fontsMap.get("Body").deriveFont(14f));
        label_TotalMDef.setForeground(new java.awt.Color(242, 242, 242));
        label_TotalMDef.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalMDef.setText("0");
        panel_SubTotal.add(label_TotalMDef);
        label_TotalMDef.setBounds(0, 50, 80, 30);

        label_TotalCC.setFont(fontsMap.get("Body").deriveFont(14f));
        label_TotalCC.setForeground(new java.awt.Color(242, 242, 242));
        label_TotalCC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalCC.setText("0");
        panel_SubTotal.add(label_TotalCC);
        label_TotalCC.setBounds(0, 270, 80, 30);

        label_TotalMDmg.setFont(fontsMap.get("Body").deriveFont(14f));
        label_TotalMDmg.setForeground(new java.awt.Color(242, 242, 242));
        label_TotalMDmg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalMDmg.setText("0");
        panel_SubTotal.add(label_TotalMDmg);
        label_TotalMDmg.setBounds(0, 220, 80, 30);

        label_TotalPDmg.setFont(fontsMap.get("Body").deriveFont(14f));
        label_TotalPDmg.setForeground(new java.awt.Color(242, 242, 242));
        label_TotalPDmg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_TotalPDmg.setText("0");
        panel_SubTotal.add(label_TotalPDmg);
        label_TotalPDmg.setBounds(0, 170, 80, 30);

        panel_Inventory.add(panel_SubTotal);
        panel_SubTotal.setBounds(310, 80, 80, 300);

        panel_Dashes2.setOpaque(false);
        panel_Dashes2.setLayout(null);

        label_Dash15.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash15.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash15.setText("-");
        panel_Dashes2.add(label_Dash15);
        label_Dash15.setBounds(0, 270, 30, 30);

        label_Dash14.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash14.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash14.setText("-");
        panel_Dashes2.add(label_Dash14);
        label_Dash14.setBounds(0, 220, 30, 30);

        label_Dash13.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash13.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash13.setText("-");
        panel_Dashes2.add(label_Dash13);
        label_Dash13.setBounds(0, 170, 30, 30);

        label_Dash12.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash12.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash12.setText("-");
        panel_Dashes2.add(label_Dash12);
        label_Dash12.setBounds(0, 50, 30, 30);

        label_Dash11.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Dash11.setForeground(new java.awt.Color(242, 242, 242));
        label_Dash11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Dash11.setText("-");
        panel_Dashes2.add(label_Dash11);
        label_Dash11.setBounds(0, 0, 30, 30);

        panel_Inventory.add(panel_Dashes2);
        panel_Dashes2.setBounds(430, 80, 30, 300);

        panel_SubGearAddition.setOpaque(false);
        panel_SubGearAddition.setLayout(null);

        label_GearPDef.setFont(fontsMap.get("Body").deriveFont(14f));
        label_GearPDef.setForeground(new java.awt.Color(242, 242, 242));
        label_GearPDef.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearPDef.setText("0");
        panel_SubGearAddition.add(label_GearPDef);
        label_GearPDef.setBounds(0, 0, 80, 30);

        label_GearMDef.setFont(fontsMap.get("Body").deriveFont(14f));
        label_GearMDef.setForeground(new java.awt.Color(242, 242, 242));
        label_GearMDef.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearMDef.setText("0");
        panel_SubGearAddition.add(label_GearMDef);
        label_GearMDef.setBounds(0, 50, 80, 30);

        label_GearPDmg.setFont(fontsMap.get("Body").deriveFont(14f));
        label_GearPDmg.setForeground(new java.awt.Color(242, 242, 242));
        label_GearPDmg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearPDmg.setText("0");
        panel_SubGearAddition.add(label_GearPDmg);
        label_GearPDmg.setBounds(0, 170, 80, 30);

        label_GearMDmg.setFont(fontsMap.get("Body").deriveFont(14f));
        label_GearMDmg.setForeground(new java.awt.Color(242, 242, 242));
        label_GearMDmg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearMDmg.setText("0");
        panel_SubGearAddition.add(label_GearMDmg);
        label_GearMDmg.setBounds(0, 220, 80, 30);

        label_GearCC.setFont(fontsMap.get("Body").deriveFont(14f));
        label_GearCC.setForeground(new java.awt.Color(242, 242, 242));
        label_GearCC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GearCC.setText("0");
        panel_SubGearAddition.add(label_GearCC);
        label_GearCC.setBounds(0, 270, 80, 30);

        panel_Inventory.add(panel_SubGearAddition);
        panel_SubGearAddition.setBounds(500, 80, 80, 300);

        button_Skills.setBackground(new java.awt.Color(67, 67, 79));
        button_Skills.setFont(fontsMap.get("Body").deriveFont(14f));
        button_Skills.setForeground(new java.awt.Color(242, 242, 242));
        button_Skills.setText("Skills");
        panel_Inventory.add(button_Skills);
        button_Skills.setBounds(190, 390, 200, 40);
        button_Skills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SkillsActionPerformed(evt);
            }
        });

        panel_Main.add(panel_Inventory);
        panel_Inventory.setBounds(5, 60, 590, 440);

        panel_Skills.setBackground(new java.awt.Color(25, 25, 33));
        panel_Skills.setLayout(null);

        label_BasicAttackName.setFont(fontsMap.get("Body"));
        label_BasicAttackName.setForeground(new java.awt.Color(242, 242, 242));
        label_BasicAttackName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_BasicAttackName.setText("Basic Attack");
        panel_Skills.add(label_BasicAttackName);
        label_BasicAttackName.setBounds(20, 50, 130, 50);

        label_Skill1Name.setFont(fontsMap.get("Body"));
        label_Skill1Name.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill1Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill1Name.setText("Skill1");
        panel_Skills.add(label_Skill1Name);
        label_Skill1Name.setBounds(160, 50, 130, 50);

        label_Skill2Name.setFont(fontsMap.get("Body"));
        label_Skill2Name.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill2Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill2Name.setText("Skill2");
        panel_Skills.add(label_Skill2Name);
        label_Skill2Name.setBounds(310, 50, 130, 50);

        label_Skill3Name.setFont(fontsMap.get("Body"));
        label_Skill3Name.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill3Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill3Name.setText("Skill3");
        panel_Skills.add(label_Skill3Name);
        label_Skill3Name.setBounds(450, 50, 130, 50);

        label_BasicAttackLevel.setFont(fontsMap.get("Body").deriveFont(12f));
        label_BasicAttackLevel.setForeground(new java.awt.Color(242, 242, 242));
        label_BasicAttackLevel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_BasicAttackLevel.setText("LVL [UNUSED]");
        panel_Skills.add(label_BasicAttackLevel);
        label_BasicAttackLevel.setBounds(20, 110, 130, 30);

        label_Skill1Level.setFont(fontsMap.get("Body").deriveFont(12f));
        label_Skill1Level.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill1Level.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill1Level.setText("LVL [UNUSED]");
        panel_Skills.add(label_Skill1Level);
        label_Skill1Level.setBounds(160, 110, 130, 30);

        label_Skill2Level.setFont(fontsMap.get("Body").deriveFont(12f));
        label_Skill2Level.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill2Level.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill2Level.setText("LVL [UNUSED]");
        panel_Skills.add(label_Skill2Level);
        label_Skill2Level.setBounds(310, 110, 130, 30);

        label_Skill3Level.setFont(fontsMap.get("Body").deriveFont(12f));
        label_Skill3Level.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill3Level.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill3Level.setText("LVL [UNUSED]");
        panel_Skills.add(label_Skill3Level);
        label_Skill3Level.setBounds(450, 110, 130, 30);

        label_BasicAttackDmg.setFont(fontsMap.get("Body").deriveFont(12f));
        label_BasicAttackDmg.setForeground(new java.awt.Color(242, 242, 242));
        label_BasicAttackDmg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_BasicAttackDmg.setText("Dmg");
        panel_Skills.add(label_BasicAttackDmg);
        label_BasicAttackDmg.setBounds(20, 140, 130, 30);

        label_Skill1Dmg.setFont(fontsMap.get("Body").deriveFont(12f));
        label_Skill1Dmg.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill1Dmg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill1Dmg.setText("Dmg");
        panel_Skills.add(label_Skill1Dmg);
        label_Skill1Dmg.setBounds(160, 140, 130, 30);

        label_Skill2Dmg.setFont(fontsMap.get("Body").deriveFont(12f));
        label_Skill2Dmg.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill2Dmg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill2Dmg.setText("Dmg");
        panel_Skills.add(label_Skill2Dmg);
        label_Skill2Dmg.setBounds(310, 140, 130, 30);

        label_Skill3Dmg.setFont(fontsMap.get("Body").deriveFont(12f));
        label_Skill3Dmg.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill3Dmg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill3Dmg.setText("Dmg");
        panel_Skills.add(label_Skill3Dmg);
        label_Skill3Dmg.setBounds(450, 140, 130, 30);

        label_BasicAttackCost.setFont(fontsMap.get("Body").deriveFont(12f));
        label_BasicAttackCost.setForeground(new java.awt.Color(242, 242, 242));
        label_BasicAttackCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_BasicAttackCost.setText("Cost");
        panel_Skills.add(label_BasicAttackCost);
        label_BasicAttackCost.setBounds(20, 170, 130, 30);

        label_Skill1Cost.setFont(fontsMap.get("Body").deriveFont(12f));
        label_Skill1Cost.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill1Cost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill1Cost.setText("Cost");
        panel_Skills.add(label_Skill1Cost);
        label_Skill1Cost.setBounds(160, 170, 130, 30);

        label_Skill2Cost.setFont(fontsMap.get("Body").deriveFont(12f));
        label_Skill2Cost.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill2Cost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill2Cost.setText("Cost");
        panel_Skills.add(label_Skill2Cost);
        label_Skill2Cost.setBounds(310, 170, 130, 30);

        label_Skill3Cost.setFont(fontsMap.get("Body").deriveFont(12f));
        label_Skill3Cost.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill3Cost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill3Cost.setText("Cost");
        panel_Skills.add(label_Skill3Cost);
        label_Skill3Cost.setBounds(450, 170, 130, 30);

        label_BasicAttackInflict.setFont(fontsMap.get("Body").deriveFont(12f));
        label_BasicAttackInflict.setForeground(new java.awt.Color(242, 242, 242));
        label_BasicAttackInflict.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_BasicAttackInflict.setText("Inflict");
        panel_Skills.add(label_BasicAttackInflict);
        label_BasicAttackInflict.setBounds(20, 200, 130, 30);

        label_Skill1Inflict.setFont(fontsMap.get("Body").deriveFont(12f));
        label_Skill1Inflict.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill1Inflict.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill1Inflict.setText("Inflict");
        panel_Skills.add(label_Skill1Inflict);
        label_Skill1Inflict.setBounds(160, 200, 130, 30);

        label_Skill2Inflict.setFont(fontsMap.get("Body").deriveFont(12f));
        label_Skill2Inflict.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill2Inflict.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill2Inflict.setText("Inflict");
        panel_Skills.add(label_Skill2Inflict);
        label_Skill2Inflict.setBounds(310, 200, 130, 30);

        label_Skill3Inflict.setFont(fontsMap.get("Body").deriveFont(12f));
        label_Skill3Inflict.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill3Inflict.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill3Inflict.setText("Inflict");
        panel_Skills.add(label_Skill3Inflict);
        label_Skill3Inflict.setBounds(450, 200, 130, 30);

        label_BasicAttackDescription.setFont(fontsMap.get("Body").deriveFont(14f));
        label_BasicAttackDescription.setForeground(new java.awt.Color(242, 242, 242));
        label_BasicAttackDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_BasicAttackDescription.setText("Description");
        panel_Skills.add(label_BasicAttackDescription);
        label_BasicAttackDescription.setBounds(20, 240, 130, 140);

        label_Skill1Description.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Skill1Description.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill1Description.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill1Description.setText("Description");
        panel_Skills.add(label_Skill1Description);
        label_Skill1Description.setBounds(160, 240, 130, 140);

        label_Skill2Description.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Skill2Description.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill2Description.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill2Description.setText("Description");
        panel_Skills.add(label_Skill2Description);
        label_Skill2Description.setBounds(310, 240, 130, 140);

        label_Skill3Description.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Skill3Description.setForeground(new java.awt.Color(242, 242, 242));
        label_Skill3Description.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Skill3Description.setText("Description");
        panel_Skills.add(label_Skill3Description);
        label_Skill3Description.setBounds(450, 240, 130, 140);

        panel_Main.add(panel_Skills);
        panel_Skills.setBounds(5, 60, 590, 440);

        panel_Game.setBackground(new java.awt.Color(25, 25, 33));
        panel_Game.setLayout(null);

        button_Quest.setBackground(new java.awt.Color(67, 67, 79));
        button_Quest.setFont(fontsMap.get("Header").deriveFont(24f));
        button_Quest.setForeground(new java.awt.Color(242, 242, 242));
        button_Quest.setText("Quest");
        button_Quest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_QuestActionPerformed(evt);
            }
        });
        panel_Game.add(button_Quest);
        button_Quest.setBounds(10, 10, 150, 40);

        button_Travel.setBackground(new java.awt.Color(67, 67, 79));
        button_Travel.setFont(fontsMap.get("Header").deriveFont(32f));
        button_Travel.setForeground(new java.awt.Color(242, 242, 242));
        button_Travel.setText("Travel");
        button_Travel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TravelActionPerformed(evt);
            }
        });
        panel_Game.add(button_Travel);
        button_Travel.setBounds(170, 390, 250, 40);

        button_Inventory.setBackground(new java.awt.Color(67, 67, 79));
        button_Inventory.setFont(fontsMap.get("Header").deriveFont(24f));
        button_Inventory.setForeground(new java.awt.Color(242, 242, 242));
        button_Inventory.setText("Inventory");
        button_Inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_InventoryActionPerformed(evt);
            }
        });
        panel_Game.add(button_Inventory);
        button_Inventory.setBounds(10, 390, 150, 40);

        button_Status.setBackground(new java.awt.Color(67, 67, 79));
        button_Status.setFont(fontsMap.get("Header").deriveFont(24f));
        button_Status.setForeground(new java.awt.Color(242, 242, 242));
        button_Status.setText("Status");
        button_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_StatusActionPerformed(evt);
            }
        });
        panel_Game.add(button_Status);
        button_Status.setBounds(430, 390, 150, 40);

        panel_PlayerStats.setBackground(new java.awt.Color(67, 67, 79, 179));
        panel_PlayerStats.setLayout(null);

        label_GameCurrency.setBackground(new java.awt.Color(81, 81, 93));
        label_GameCurrency.setFont(fontsMap.get("Body").deriveFont(12f));
        label_GameCurrency.setForeground(new java.awt.Color(242, 242, 242));
        label_GameCurrency.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GameCurrency.setText("Coins: ");
        label_GameCurrency.setOpaque(true);
        panel_PlayerStats.add(label_GameCurrency);
        label_GameCurrency.setBounds(10, 250, 300, 50);

        label_GameXP.setBackground(new java.awt.Color(81, 81, 93));
        label_GameXP.setFont(fontsMap.get("Body").deriveFont(12f));
        label_GameXP.setForeground(new java.awt.Color(242, 242, 242));
        label_GameXP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GameXP.setText("XP:");
        label_GameXP.setOpaque(true);
        panel_PlayerStats.add(label_GameXP);
        label_GameXP.setBounds(10, 190, 300, 50);

        label_GameMP.setBackground(new java.awt.Color(81, 81, 93));
        label_GameMP.setFont(fontsMap.get("Body").deriveFont(12f));
        label_GameMP.setForeground(new java.awt.Color(242, 242, 242));
        label_GameMP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GameMP.setText("MP:");
        label_GameMP.setOpaque(true);
        panel_PlayerStats.add(label_GameMP);
        label_GameMP.setBounds(10, 130, 300, 50);

        label_GameHP.setBackground(new java.awt.Color(81, 81, 93));
        label_GameHP.setFont(fontsMap.get("Body").deriveFont(12f));
        label_GameHP.setForeground(new java.awt.Color(242, 242, 242));
        label_GameHP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_GameHP.setText("HP:");
        label_GameHP.setOpaque(true);
        panel_PlayerStats.add(label_GameHP);
        label_GameHP.setBounds(10, 70, 300, 50);

        label_Location.setBackground(new java.awt.Color(81, 81, 93));
        label_Location.setFont(fontsMap.get("Body").deriveFont(12f));
        label_Location.setForeground(new java.awt.Color(242, 242, 242));
        label_Location.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Location.setText("Location: ");
        label_Location.setOpaque(true);
        panel_PlayerStats.add(label_Location);
        label_Location.setBounds(10, 10, 300, 50);

        panel_Game.add(panel_PlayerStats);
        panel_PlayerStats.setBounds(10, 60, 320, 310);

        panel_LocationPanel.setBackground(new java.awt.Color(67, 67, 79, 179));
        panel_LocationPanel.setLayout(null);

        button_Place1.setBackground(new java.awt.Color(81, 81, 93));
        button_Place1.setFont(fontsMap.get("Header").deriveFont(28f));
        button_Place1.setForeground(new java.awt.Color(242, 242, 242));
        button_Place1.setText("Village Elder");
        button_Place1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Place1ActionPerformed(evt);
            }
        });
        panel_LocationPanel.add(button_Place1);
        button_Place1.setBounds(10, 10, 220, 90);

        button_Place2.setBackground(new java.awt.Color(81, 81, 93));
        button_Place2.setFont(fontsMap.get("Header").deriveFont(28f));
        button_Place2.setForeground(new java.awt.Color(242, 242, 242));
        button_Place2.setText("Travelling Merchant");
        button_Place2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Place2ActionPerformed(evt);
            }
        });
        panel_LocationPanel.add(button_Place2);
        button_Place2.setBounds(10, 110, 220, 90);

        button_Place3.setBackground(new java.awt.Color(81, 81, 93));
        button_Place3.setFont(fontsMap.get("Header").deriveFont(28f));
        button_Place3.setForeground(new java.awt.Color(242, 242, 242));
        button_Place3.setText("Home");
        button_Place3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_Place3ActionPerformed(evt);
            }
        });
        panel_LocationPanel.add(button_Place3);
        button_Place3.setBounds(10, 210, 220, 90);

        panel_Game.add(panel_LocationPanel);
        panel_LocationPanel.setBounds(340, 60, 240, 310);

        label_GameBackground.setForeground(new java.awt.Color(242, 242, 242));
        panel_Game.add(label_GameBackground);
        label_GameBackground.setBounds(-3, 0, 600, 450);

        panel_Main.add(panel_Game);
        panel_Game.setBounds(5, 60, 590, 440);

        panel_Quest.setBackground(new java.awt.Color(25, 25, 33));
        panel_Quest.setLayout(null);

        label_QuestTitle.setFont(fontsMap.get("Body"));
        label_QuestTitle.setForeground(new java.awt.Color(242, 242, 242));
        label_QuestTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_QuestTitle.setText("Title");
        panel_Quest.add(label_QuestTitle);
        label_QuestTitle.setBounds(20, 60, 550, 70);

        label_QuestBody.setFont(fontsMap.get("Body"));
        label_QuestBody.setForeground(new java.awt.Color(242, 242, 242));
        label_QuestBody.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_QuestBody.setText("Body");
        panel_Quest.add(label_QuestBody);
        label_QuestBody.setBounds(20, 140, 550, 150);

        label_QuestReward.setFont(fontsMap.get("Body"));
        label_QuestReward.setForeground(new java.awt.Color(242, 242, 242));
        label_QuestReward.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_QuestReward.setText("Reward");
        panel_Quest.add(label_QuestReward);
        label_QuestReward.setBounds(20, 300, 550, 60);

        label_QuestCompleted.setFont(fontsMap.get("Body"));
        label_QuestCompleted.setForeground(new java.awt.Color(242, 242, 242));
        label_QuestCompleted.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_QuestCompleted.setText("Completed");
        panel_Quest.add(label_QuestCompleted);
        label_QuestCompleted.setBounds(20, 370, 550, 60);

        panel_Main.add(panel_Quest);
        panel_Quest.setBounds(5, 60, 590, 440);

        panel_Storyline.setBackground(new java.awt.Color(25, 25, 33));
        panel_Storyline.setLayout(null);

        panel_PickRescue.setBackground(new java.awt.Color(67, 67, 79, 179));
        panel_PickRescue.setOpaque(false);
        panel_PickRescue.setLayout(null);

        button_Princess.setBackground(new java.awt.Color(81, 81, 93));
        button_Princess.setFont(fontsMap.get("Header").deriveFont(28f));
        button_Princess.setForeground(new java.awt.Color(242, 242, 242));
        button_Princess.setText("Princess");
        panel_PickRescue.add(button_Princess);
        button_Princess.setBounds(0, 0, 280, 50);
        button_Princess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_PrincessActionPerformed(evt);
            }
        });

        button_Prince.setBackground(new java.awt.Color(81, 81, 93));
        button_Prince.setFont(fontsMap.get("Header").deriveFont(28f));
        button_Prince.setForeground(new java.awt.Color(242, 242, 242));
        button_Prince.setText("Prince");
        panel_PickRescue.add(button_Prince);
        button_Prince.setBounds(290, 0, 280, 50);
        button_Prince.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_PrinceActionPerformed(evt);
            }
        });

        panel_Storyline.add(panel_PickRescue);
        panel_PickRescue.setBounds(10, 180, 570, 50);

        textField_NameField.setBackground(new java.awt.Color(69, 69, 74));
        textField_NameField.setForeground(new java.awt.Color(242, 242, 242));
        textField_NameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textField_NameField.setText("Adventurer");
        panel_Storyline.add(textField_NameField);
        textField_NameField.setBounds(30, 180, 540, 50);
        textField_NameField.setFont(fontsMap.get("Body"));

        label_Title.setFont(fontsMap.get("Body"));
        label_Title.setForeground(new java.awt.Color(242, 242, 242));
        label_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Title.setText("Talker");
        panel_Storyline.add(label_Title);
        label_Title.setBounds(20, 110, 550, 40);

        label_Talker.setFont(fontsMap.get("Body").deriveFont(28f));
        label_Talker.setForeground(new java.awt.Color(242, 242, 242));
        label_Talker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Talker.setText("Talker");
        panel_Storyline.add(label_Talker);
        label_Talker.setBounds(20, 50, 550, 60);

        label_StorylineText.setFont(fontsMap.get("Body").deriveFont(14f));
        label_StorylineText.setForeground(new java.awt.Color(242, 242, 242));
        label_StorylineText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_StorylineText.setText("[CLICK TO START]");
        panel_Storyline.add(label_StorylineText);
        label_StorylineText.setBounds(20, 170, 550, 210);

        button_Confirm.setBackground(new java.awt.Color(67, 67, 79));
        button_Confirm.setFont(fontsMap.get("Body").deriveFont(14f));
        button_Confirm.setForeground(new java.awt.Color(242, 242, 242));
        button_Confirm.setText("Confirm");
        panel_Storyline.add(button_Confirm);
        button_Confirm.setBounds(190, 390, 200, 40);
        button_Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ConfirmActionPerformed(evt);
            }
        });

        panel_Main.add(panel_Storyline);
        panel_Storyline.setBounds(5, 60, 590, 440);
        panel_Storyline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_StorylineMouseClicked(evt);
            }
        });

        panel_StartingGear.setBackground(new java.awt.Color(25, 25, 33));
        panel_StartingGear.setLayout(null);

        button_IronSword.setBackground(new java.awt.Color(67, 67, 79));
        button_IronSword.setFont(fontsMap.get("Header").deriveFont(24f));
        button_IronSword.setForeground(new java.awt.Color(242, 242, 242));
        button_IronSword.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Iron Sword</p>  </body> </html>");
        button_IronSword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_IronSwordActionPerformed(evt);
            }
        });
        panel_StartingGear.add(button_IronSword);
        button_IronSword.setBounds(10, 70, 180, 50);

        label_IronSword.setFont(fontsMap.get("Body").deriveFont(14f));
        label_IronSword.setForeground(new java.awt.Color(242, 242, 242));
        label_IronSword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_IronSword.setText("<html><body><p align=\"center\">A warrior's proven weapon. It deals great amounts of physical damage consistently, perfect for the common brawler.<br> (Blades increase strength points and physical damage)</p></body></html> ");
        label_IronSword.setToolTipText("");
        panel_StartingGear.add(label_IronSword);
        label_IronSword.setBounds(10, 130, 180, 240);

        button_SimpleBow.setBackground(new java.awt.Color(67, 67, 79));
        button_SimpleBow.setFont(fontsMap.get("Header").deriveFont(24f));
        button_SimpleBow.setForeground(new java.awt.Color(242, 242, 242));
        button_SimpleBow.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Simple Bow</p>  </body> </html>");
        button_SimpleBow.setToolTipText("");
        button_SimpleBow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SimpleBowActionPerformed(evt);
            }
        });
        panel_StartingGear.add(button_SimpleBow);
        button_SimpleBow.setBounds(400, 70, 180, 50);

        label_SimpleBow.setFont(fontsMap.get("Body").deriveFont(14f));
        label_SimpleBow.setForeground(new java.awt.Color(242, 242, 242));
        label_SimpleBow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_SimpleBow.setText("<html><body><p align=\"center\">A ranger's reliable weapon. It deals less physical damage but improves the chances of a critical hit.<br> (Bows increase agility points and crit chances)</p></body></html>");
        label_SimpleBow.setToolTipText("");
        panel_StartingGear.add(label_SimpleBow);
        label_SimpleBow.setBounds(400, 130, 180, 240);

        button_CrudeWand.setBackground(new java.awt.Color(67, 67, 79));
        button_CrudeWand.setFont(fontsMap.get("Header").deriveFont(24f));
        button_CrudeWand.setForeground(new java.awt.Color(242, 242, 242));
        button_CrudeWand.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Crude Wand</p>  </body> </html>");
        button_CrudeWand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CrudeWandActionPerformed(evt);
            }
        });
        panel_StartingGear.add(button_CrudeWand);
        button_CrudeWand.setBounds(200, 70, 190, 50);

        label_CrudeWand.setFont(fontsMap.get("Body").deriveFont(14f));
        label_CrudeWand.setForeground(new java.awt.Color(242, 242, 242));
        label_CrudeWand.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_CrudeWand.setText("<html><body><p align=\"center\">A mage's handy weapon. It deals magical damage, though a mage's true potential lies in their magical abilities.<br> (Wands/ staves increase intelligence points, magical damage, and magical defence)</p></body></html> ");
        label_CrudeWand.setToolTipText("");
        panel_StartingGear.add(label_CrudeWand);
        label_CrudeWand.setBounds(200, 130, 190, 240);

        panel_Main.add(panel_StartingGear);
        panel_StartingGear.setBounds(5, 60, 590, 440);

        panel_AffinitiesMenu.setBackground(new java.awt.Color(25, 25, 33));
        panel_AffinitiesMenu.setLayout(null);

        button_Sanitas.setBackground(new java.awt.Color(67, 67, 79));
        button_Sanitas.setFont(fontsMap.get("Header").deriveFont(24f)
        );
        button_Sanitas.setForeground(new java.awt.Color(242, 242, 242));
        button_Sanitas.setText("<html>Sanitas");
        button_Sanitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SanitasActionPerformed(evt);
            }
        });
        panel_AffinitiesMenu.add(button_Sanitas);
        button_Sanitas.setBounds(20, 70, 100, 50);

        label_Sanitas.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Sanitas.setForeground(new java.awt.Color(242, 242, 242));
        label_Sanitas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Sanitas.setText("<html><p align=\"center\">Sanitas blesses the holders of her affinity by improving their constitution.<br> (Health Points increases every level)</p></body></html> ");
        label_Sanitas.setToolTipText("");
        panel_AffinitiesMenu.add(label_Sanitas);
        label_Sanitas.setBounds(20, 130, 100, 260);

        button_Virtus.setBackground(new java.awt.Color(67, 67, 79));
        button_Virtus.setFont(fontsMap.get("Header").deriveFont(24f)
        );
        button_Virtus.setForeground(new java.awt.Color(242, 242, 242));
        button_Virtus.setText("Virtus");
        button_Virtus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_VirtusActionPerformed(evt);
            }
        });
        panel_AffinitiesMenu.add(button_Virtus);
        button_Virtus.setBounds(130, 70, 100, 50);

        label_Virtus.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Virtus.setForeground(new java.awt.Color(242, 242, 242));
        label_Virtus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Virtus.setText("<html><body><p align=\"center\">Virtus blesses the holders of his affinity by improving their might.<br> (Strength Points increases every level)</p></body></html> ");
        label_Virtus.setToolTipText("");
        panel_AffinitiesMenu.add(label_Virtus);
        label_Virtus.setBounds(130, 130, 100, 260);

        button_Tutela.setBackground(new java.awt.Color(67, 67, 79));
        button_Tutela.setFont(fontsMap.get("Header").deriveFont(24f)
        );
        button_Tutela.setForeground(new java.awt.Color(242, 242, 242));
        button_Tutela.setText("<html>Tutela");
        button_Tutela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TutelaActionPerformed(evt);
            }
        });
        panel_AffinitiesMenu.add(button_Tutela);
        button_Tutela.setBounds(240, 70, 100, 50);

        label_Tutela.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Tutela.setForeground(new java.awt.Color(242, 242, 242));
        label_Tutela.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Tutela.setText("<html><body><p align=\"center\">Tutela blesses the holders of her affinity by improving their fortitude.<br> (Defense Points increases every level)</p></body></html> ");
        label_Tutela.setToolTipText("");
        panel_AffinitiesMenu.add(label_Tutela);
        label_Tutela.setBounds(240, 130, 100, 260);

        button_Madeis.setBackground(new java.awt.Color(67, 67, 79));
        button_Madeis.setFont(fontsMap.get("Header").deriveFont(24f)
        );
        button_Madeis.setForeground(new java.awt.Color(242, 242, 242));
        button_Madeis.setText("<html>Madeis");
        button_Madeis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_MadeisActionPerformed(evt);
            }
        });
        panel_AffinitiesMenu.add(button_Madeis);
        button_Madeis.setBounds(350, 70, 100, 50);

        label_Madeis.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Madeis.setForeground(new java.awt.Color(242, 242, 242));
        label_Madeis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Madeis.setText("<html><body><p align=\"center\">Madeis blesses the holders of his affinity by improving their magical abilities.<br> (Intelligence Points increases every level)</p></body></html> ");
        label_Madeis.setToolTipText("");
        panel_AffinitiesMenu.add(label_Madeis);
        label_Madeis.setBounds(350, 130, 100, 260);

        button_Celeritas.setBackground(new java.awt.Color(67, 67, 79));
        button_Celeritas.setFont(fontsMap.get("Header").deriveFont(24f)
        );
        button_Celeritas.setForeground(new java.awt.Color(242, 242, 242));
        button_Celeritas.setText("<html>Celeritas");
        button_Celeritas.setToolTipText("");
        button_Celeritas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CeleritasActionPerformed(evt);
            }
        });
        panel_AffinitiesMenu.add(button_Celeritas);
        button_Celeritas.setBounds(460, 70, 100, 50);

        label_Celeritas.setFont(fontsMap.get("Body").deriveFont(14f));
        label_Celeritas.setForeground(new java.awt.Color(242, 242, 242));
        label_Celeritas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Celeritas.setText("<html><body><p align=\"center\">Sanitas blesses the holders of her affinity by improving their constitution.<br> (Agility Points increases every level)</p></body></html> ");
        label_Celeritas.setToolTipText("");
        panel_AffinitiesMenu.add(label_Celeritas);
        label_Celeritas.setBounds(460, 130, 100, 260);

        panel_Main.add(panel_AffinitiesMenu);
        panel_AffinitiesMenu.setBounds(5, 60, 590, 440);

        panel_Shop.setBackground(new java.awt.Color(25, 25, 33));
        panel_Shop.setLayout(null);

        label_ShopLocation.setBackground(new java.awt.Color(67, 67, 79));
        label_ShopLocation.setFont(fontsMap.get("Body"));
        label_ShopLocation.setForeground(new java.awt.Color(242, 242, 242));
        label_ShopLocation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_ShopLocation.setText("Location");
        label_ShopLocation.setOpaque(true);
        panel_Shop.add(label_ShopLocation);
        label_ShopLocation.setBounds(190, 50, 220, 50);

        label_ShopCurrency.setBackground(new java.awt.Color(67, 67, 79));
        label_ShopCurrency.setFont(fontsMap.get("Body"));
        label_ShopCurrency.setForeground(new java.awt.Color(242, 242, 242));
        label_ShopCurrency.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_ShopCurrency.setText("Coins: ");
        label_ShopCurrency.setOpaque(true);
        panel_Shop.add(label_ShopCurrency);
        label_ShopCurrency.setBounds(10, 110, 570, 50);

        button_BuySword.setBackground(new java.awt.Color(81, 81, 93));
        button_BuySword.setFont(fontsMap.get("Body").deriveFont(10f));
        button_BuySword.setForeground(new java.awt.Color(242, 242, 242));
        button_BuySword.setText("Sword");
        button_BuySword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_BuySwordActionPerformed(evt);
            }
        });
        panel_Shop.add(button_BuySword);
        button_BuySword.setBounds(10, 170, 280, 50);

        button_BuyBow.setBackground(new java.awt.Color(81, 81, 93));
        button_BuyBow.setFont(fontsMap.get("Body").deriveFont(10f));
        button_BuyBow.setForeground(new java.awt.Color(242, 242, 242));
        button_BuyBow.setText("Bow");
        button_BuyBow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_BuyBowActionPerformed(evt);
            }
        });
        panel_Shop.add(button_BuyBow);
        button_BuyBow.setBounds(10, 240, 280, 50);

        button_BuyWand.setBackground(new java.awt.Color(81, 81, 93));
        button_BuyWand.setFont(fontsMap.get("Body").deriveFont(10f));
        button_BuyWand.setForeground(new java.awt.Color(242, 242, 242));
        button_BuyWand.setText("Wand");
        button_BuyWand.setToolTipText("");
        button_BuyWand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_BuyWandActionPerformed(evt);
            }
        });
        panel_Shop.add(button_BuyWand);
        button_BuyWand.setBounds(10, 310, 280, 50);

        button_BuyArmor.setBackground(new java.awt.Color(81, 81, 93));
        button_BuyArmor.setFont(fontsMap.get("Body").deriveFont(10f));
        button_BuyArmor.setForeground(new java.awt.Color(242, 242, 242));
        button_BuyArmor.setText("Armor");
        button_BuyArmor.setToolTipText("");
        button_BuyArmor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_BuyArmorActionPerformed(evt);
            }
        });
        panel_Shop.add(button_BuyArmor);
        button_BuyArmor.setBounds(10, 380, 280, 50);

        button_EquipSword.setBackground(new java.awt.Color(81, 81, 93));
        button_EquipSword.setFont(fontsMap.get("Body").deriveFont(10f));
        button_EquipSword.setForeground(new java.awt.Color(242, 242, 242));
        button_EquipSword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_EquipSwordActionPerformed(evt);
            }
        });
        panel_Shop.add(button_EquipSword);
        button_EquipSword.setBounds(300, 170, 280, 50);

        button_EquipBow.setBackground(new java.awt.Color(81, 81, 93));
        button_EquipBow.setFont(fontsMap.get("Body").deriveFont(10f));
        button_EquipBow.setForeground(new java.awt.Color(242, 242, 242));
        button_EquipBow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_EquipBowActionPerformed(evt);
            }
        });
        panel_Shop.add(button_EquipBow);
        button_EquipBow.setBounds(300, 240, 280, 50);

        button_EquipWand.setBackground(new java.awt.Color(81, 81, 93));
        button_EquipWand.setFont(fontsMap.get("Body").deriveFont(10f));
        button_EquipWand.setForeground(new java.awt.Color(242, 242, 242));
        button_EquipWand.setToolTipText("");
        button_EquipWand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_EquipWandActionPerformed(evt);
            }
        });
        panel_Shop.add(button_EquipWand);
        button_EquipWand.setBounds(300, 310, 280, 50);

        button_EquipArmor.setBackground(new java.awt.Color(81, 81, 93));
        button_EquipArmor.setFont(fontsMap.get("Body").deriveFont(10f));
        button_EquipArmor.setForeground(new java.awt.Color(242, 242, 242));
        button_EquipArmor.setToolTipText("");
        button_EquipArmor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_EquipArmorActionPerformed(evt);
            }
        });
        panel_Shop.add(button_EquipArmor);
        button_EquipArmor.setBounds(300, 380, 280, 50);

        panel_Main.add(panel_Shop);
        panel_Shop.setBounds(5, 60, 590, 440);

        panel_ChooseSkill.setBackground(new java.awt.Color(25, 25, 33));
        panel_ChooseSkill.setLayout(null);

        button_ChooseSkill1.setBackground(new java.awt.Color(67, 67, 79));
        button_ChooseSkill1.setFont(fontsMap.get("Header").deriveFont(24f));
        button_ChooseSkill1.setForeground(new java.awt.Color(242, 242, 242));
        button_ChooseSkill1.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Iron Sword</p>  </body> </html>");
        button_ChooseSkill1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ChooseSkill1ActionPerformed(evt);
            }
        });
        panel_ChooseSkill.add(button_ChooseSkill1);
        button_ChooseSkill1.setBounds(10, 70, 180, 50);

        label_ChooseSkill1.setFont(fontsMap.get("Body").deriveFont(14f));
        label_ChooseSkill1.setForeground(new java.awt.Color(242, 242, 242));
        label_ChooseSkill1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_ChooseSkill1.setText("<html><body><p align=\"center\">A warrior's proven weapon. It deals great amounts of physical damage consistently, perfect for the common brawler.<br> (Blades increase strength points and physical damage)</p></body></html> ");
        label_ChooseSkill1.setToolTipText("");
        panel_ChooseSkill.add(label_ChooseSkill1);
        label_ChooseSkill1.setBounds(10, 130, 180, 240);

        button_ChooseSkill2.setBackground(new java.awt.Color(67, 67, 79));
        button_ChooseSkill2.setFont(fontsMap.get("Header").deriveFont(24f));
        button_ChooseSkill2.setForeground(new java.awt.Color(242, 242, 242));
        button_ChooseSkill2.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Crude Wand</p>  </body> </html>");
        button_ChooseSkill2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ChooseSkill2ActionPerformed(evt);
            }
        });
        panel_ChooseSkill.add(button_ChooseSkill2);
        button_ChooseSkill2.setBounds(200, 70, 190, 50);

        label_ChooseSkill2.setFont(fontsMap.get("Body").deriveFont(14f));
        label_ChooseSkill2.setForeground(new java.awt.Color(242, 242, 242));
        label_ChooseSkill2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_ChooseSkill2.setText("<html><body><p align=\"center\">A mage's handy weapon. It deals magical damage, though a mage's true potential lies in their magical abilities.<br> (Wands/ staves increase intelligence points, magical damage, and magical defence)</p></body></html> ");
        label_ChooseSkill2.setToolTipText("");
        panel_ChooseSkill.add(label_ChooseSkill2);
        label_ChooseSkill2.setBounds(200, 130, 190, 240);

        button_ChooseSkill3.setBackground(new java.awt.Color(67, 67, 79));
        button_ChooseSkill3.setFont(fontsMap.get("Header").deriveFont(24f));
        button_ChooseSkill3.setForeground(new java.awt.Color(242, 242, 242));
        button_ChooseSkill3.setText("<html> <head> <style> p {text-align: center;} </style> <body> <p>Simple Bow</p>  </body> </html>");
        button_ChooseSkill3.setToolTipText("");
        button_ChooseSkill3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ChooseSkill3ActionPerformed(evt);
            }
        });
        panel_ChooseSkill.add(button_ChooseSkill3);
        button_ChooseSkill3.setBounds(400, 70, 180, 50);

        label_ChooseSkill3.setFont(fontsMap.get("Body").deriveFont(14f));
        label_ChooseSkill3.setForeground(new java.awt.Color(242, 242, 242));
        label_ChooseSkill3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_ChooseSkill3.setText("<html><body><p align=\"center\">A ranger's reliable weapon. It deals less physical damage but improves the chances of a critical hit.<br> (Bows increase agility points and crit chances)</p></body></html>");
        label_ChooseSkill3.setToolTipText("");
        panel_ChooseSkill.add(label_ChooseSkill3);
        label_ChooseSkill3.setBounds(400, 130, 180, 240);

        panel_Main.add(panel_ChooseSkill);
        panel_ChooseSkill.setBounds(5, 60, 590, 440);

        panel_Combat.setBackground(new java.awt.Color(25, 25, 33));
        panel_Combat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_CombatMouseClicked(evt);
            }
        });
        panel_Combat.setLayout(null);

        label_CombatPlayer.setFont(fontsMap.get("Body").deriveFont(20f));
        label_CombatPlayer.setForeground(new java.awt.Color(242, 242, 242));
        label_CombatPlayer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_CombatPlayer.setText("Player (LVL 50)");
        panel_Combat.add(label_CombatPlayer);
        label_CombatPlayer.setBounds(10, 50, 290, 70);

        label_CombatPlayerAffinity.setFont(fontsMap.get("Body").deriveFont(14f));
        label_CombatPlayerAffinity.setForeground(new java.awt.Color(242, 242, 242));
        label_CombatPlayerAffinity.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_CombatPlayerAffinity.setText("Player Affinity");
        panel_Combat.add(label_CombatPlayerAffinity);
        label_CombatPlayerAffinity.setBounds(10, 120, 290, 40);

        label_CombatHP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_CombatHP.setForeground(new java.awt.Color(242, 242, 242));
        label_CombatHP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_CombatHP.setText("HP: 0 / 0");
        panel_Combat.add(label_CombatHP);
        label_CombatHP.setBounds(10, 160, 290, 30);

        label_CombatMP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_CombatMP.setForeground(new java.awt.Color(242, 242, 242));
        label_CombatMP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_CombatMP.setText("MP: 0 / 0");
        panel_Combat.add(label_CombatMP);
        label_CombatMP.setBounds(10, 190, 290, 30);

        label_CombatEnemy.setFont(fontsMap.get("Body").deriveFont(20f));
        label_CombatEnemy.setForeground(new java.awt.Color(242, 242, 242));
        label_CombatEnemy.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_CombatEnemy.setText("Enemy (LVL 50)");
        panel_Combat.add(label_CombatEnemy);
        label_CombatEnemy.setBounds(300, 50, 280, 70);

        label_CombatEnemyAffinity.setFont(fontsMap.get("Body").deriveFont(14f));
        label_CombatEnemyAffinity.setForeground(new java.awt.Color(242, 242, 242));
        label_CombatEnemyAffinity.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_CombatEnemyAffinity.setText("Enemy Affinity");
        panel_Combat.add(label_CombatEnemyAffinity);
        label_CombatEnemyAffinity.setBounds(300, 120, 280, 40);

        label_EnemyHP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_EnemyHP.setForeground(new java.awt.Color(242, 242, 242));
        label_EnemyHP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_EnemyHP.setText("HP: 0 / 0");
        panel_Combat.add(label_EnemyHP);
        label_EnemyHP.setBounds(300, 160, 280, 30);

        label_EnemyMP.setFont(fontsMap.get("Body").deriveFont(14f));
        label_EnemyMP.setForeground(new java.awt.Color(242, 242, 242));
        label_EnemyMP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_EnemyMP.setText("MP: 0 / 0");
        panel_Combat.add(label_EnemyMP);
        label_EnemyMP.setBounds(300, 190, 280, 30);

        button_FleeCombat.setBackground(new java.awt.Color(67, 67, 79));
        button_FleeCombat.setFont(fontsMap.get("Header").deriveFont(24f));
        button_FleeCombat.setForeground(new java.awt.Color(242, 242, 242));
        button_FleeCombat.setText("Flee");
        button_FleeCombat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_FleeCombatActionPerformed(evt);
            }
        });
        panel_Combat.add(button_FleeCombat);
        button_FleeCombat.setBounds(300, 390, 280, 40);

        button_UseSkill.setBackground(new java.awt.Color(67, 67, 79));
        button_UseSkill.setFont(fontsMap.get("Header").deriveFont(24f));
        button_UseSkill.setForeground(new java.awt.Color(242, 242, 242));
        button_UseSkill.setText("Skills");
        button_UseSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UseSkillActionPerformed(evt);
            }
        });
        panel_Combat.add(button_UseSkill);
        button_UseSkill.setBounds(10, 390, 270, 40);

        panel_CombatSkills.setBackground(new java.awt.Color(67, 67, 79));
        panel_CombatSkills.setLayout(null);

        button_UseBasicAttack.setBackground(new java.awt.Color(81, 81, 93));
        button_UseBasicAttack.setFont(fontsMap.get("Body").deriveFont(14f));
        button_UseBasicAttack.setForeground(new java.awt.Color(242, 242, 242));
        button_UseBasicAttack.setText("Skill1");
        button_UseBasicAttack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UseBasicAttackActionPerformed(evt);
            }
        });
        panel_CombatSkills.add(button_UseBasicAttack);
        button_UseBasicAttack.setBounds(10, 10, 260, 60);

        button_UseSkill1.setBackground(new java.awt.Color(81, 81, 93));
        button_UseSkill1.setFont(fontsMap.get("Body").deriveFont(14f));
        button_UseSkill1.setForeground(new java.awt.Color(242, 242, 242));
        button_UseSkill1.setText("Skill2");
        button_UseSkill1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UseSkill1ActionPerformed(evt);
            }
        });
        panel_CombatSkills.add(button_UseSkill1);
        button_UseSkill1.setBounds(290, 10, 270, 60);

        button_UseSkill2.setBackground(new java.awt.Color(81, 81, 93));
        button_UseSkill2.setFont(fontsMap.get("Body").deriveFont(14f));
        button_UseSkill2.setForeground(new java.awt.Color(242, 242, 242));
        button_UseSkill2.setText("Skill3");
        button_UseSkill2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UseSkill2ActionPerformed(evt);
            }
        });
        panel_CombatSkills.add(button_UseSkill2);
        button_UseSkill2.setBounds(10, 80, 260, 60);

        button_UseSkill3.setBackground(new java.awt.Color(81, 81, 93));
        button_UseSkill3.setFont(fontsMap.get("Body").deriveFont(14f));
        button_UseSkill3.setForeground(new java.awt.Color(242, 242, 242));
        button_UseSkill3.setText("Skill4");
        button_UseSkill3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UseSkill3ActionPerformed(evt);
            }
        });
        panel_CombatSkills.add(button_UseSkill3);
        button_UseSkill3.setBounds(290, 80, 270, 60);

        panel_Combat.add(panel_CombatSkills);
        panel_CombatSkills.setBounds(10, 230, 570, 150);

        panel_CombatLog.setBackground(new java.awt.Color(67, 67, 79));
        panel_CombatLog.setLayout(null);

        label_CombatLog.setBackground(new java.awt.Color(99, 99, 99));
        label_CombatLog.setFont(fontsMap.get("Body"));
        label_CombatLog.setForeground(new java.awt.Color(242, 242, 242));
        label_CombatLog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_CombatLog.setText("Actions");
        panel_CombatLog.add(label_CombatLog);
        label_CombatLog.setBounds(0, 0, 570, 150);

        panel_Combat.add(panel_CombatLog);
        panel_CombatLog.setBounds(10, 230, 570, 150);

        panel_Main.add(panel_Combat);
        panel_Combat.setBounds(5, 60, 590, 440);

        panel_Dungeon.setBackground(new java.awt.Color(25, 25, 33));
        panel_Dungeon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_DungeonMouseClicked(evt);
            }
        });
        panel_Dungeon.setLayout(null);

        label_DungeonLocation.setBackground(new java.awt.Color(67, 67, 79, 225));
        label_DungeonLocation.setFont(fontsMap.get("Header").deriveFont(24f));
        label_DungeonLocation.setForeground(new java.awt.Color(242, 242, 242));
        label_DungeonLocation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_DungeonLocation.setText("Location");
        label_DungeonLocation.setOpaque(true);
        panel_Dungeon.add(label_DungeonLocation);
        label_DungeonLocation.setBounds(150, 10, 290, 60);

        label_EncounterLog.setBackground(new java.awt.Color(67, 67, 79, 225));
        label_EncounterLog.setFont(fontsMap.get("Body"));
        label_EncounterLog.setForeground(new java.awt.Color(242, 242, 242));
        label_EncounterLog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_EncounterLog.setText("Encounter Log");
        label_EncounterLog.setOpaque(true);
        panel_Dungeon.add(label_EncounterLog);
        label_EncounterLog.setBounds(30, 80, 530, 230);

        button_DungeonAttack.setBackground(new java.awt.Color(67, 67, 79));
        button_DungeonAttack.setFont(fontsMap.get("Header").deriveFont(24f));
        button_DungeonAttack.setForeground(new java.awt.Color(242, 242, 242));
        button_DungeonAttack.setText("Attack");
        button_DungeonAttack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DungeonAttackActionPerformed(evt);
            }
        });
        panel_Dungeon.add(button_DungeonAttack);
        button_DungeonAttack.setBounds(70, 340, 220, 40);

        button_DungeonFlee.setBackground(new java.awt.Color(67, 67, 79));
        button_DungeonFlee.setFont(fontsMap.get("Header").deriveFont(24f));
        button_DungeonFlee.setForeground(new java.awt.Color(242, 242, 242));
        button_DungeonFlee.setText("Flee");
        button_DungeonFlee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DungeonFleeActionPerformed(evt);
            }
        });
        panel_Dungeon.add(button_DungeonFlee);
        button_DungeonFlee.setBounds(300, 340, 220, 40);

        button_DungeonReturn.setBackground(new java.awt.Color(67, 67, 79));
        button_DungeonReturn.setFont(fontsMap.get("Header").deriveFont(24f));
        button_DungeonReturn.setForeground(new java.awt.Color(242, 242, 242));
        button_DungeonReturn.setText("Return");
        panel_Dungeon.add(button_DungeonReturn);
        button_DungeonReturn.setBounds(170, 390, 220, 40);
        button_DungeonReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DungeonReturnActionPerformed(evt);
            }
        });

        label_WildernessBackground.setForeground(new java.awt.Color(242, 242, 242));
        panel_Dungeon.add(label_WildernessBackground);
        label_WildernessBackground.setBounds(-3, 0, 600, 450);

        panel_Main.add(panel_Dungeon);
        panel_Dungeon.setBounds(5, 60, 590, 440);

        panel_Intro.setBackground(new java.awt.Color(25, 25, 33));
        panel_Intro.setLayout(null);

        label_IntroHeader.setBackground(new java.awt.Color(67, 67, 79, 225));
        label_IntroHeader.setFont(fontsMap.get("Header").deriveFont(60f));
        label_IntroHeader.setForeground(new java.awt.Color(242, 242, 242));
        label_IntroHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_IntroHeader.setText("<html> <body> <p align=\"center\"> Realm of Allyria </p> </body> </html>");
        label_IntroHeader.setToolTipText("");
        label_IntroHeader.setOpaque(true);
        panel_Intro.add(label_IntroHeader);
        label_IntroHeader.setBounds(20, 70, 550, 80);

        label_IntroBody.setBackground(new java.awt.Color(67, 67, 79, 225));
        label_IntroBody.setFont(fontsMap.get("Body").deriveFont(12f));
        label_IntroBody.setForeground(new java.awt.Color(242, 242, 242));
        label_IntroBody.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_IntroBody.setText("(CLICK TO START YOUR ADVENTURE)");
        label_IntroBody.setToolTipText("");
        label_IntroBody.setOpaque(true);
        panel_Intro.add(label_IntroBody);
        label_IntroBody.setBounds(160, 380, 270, 40);

        label_IntroBackground.setForeground(new java.awt.Color(242, 242, 242));
        label_IntroBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_Intro.add(label_IntroBackground);
        label_IntroBackground.setBounds(0, 0, 590, 440);

        panel_Main.add(panel_Intro);
        panel_Intro.setBounds(5, 60, 590, 440);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="storyline stuff">

    private void panel_MainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_MainMouseClicked

        if (storylineIndex == 0) {

            // starts the game proper
            speakToNPC(-1);
            panel_Intro.setVisible(false);
            loadText("REALM OF ALLYRIA (v1.3 RELEASE VERSION)", label_Header, false, true, TextLoadingSpeeds.SLOW.textSpeed);

        }

    }//GEN-LAST:event_panel_MainMouseClicked

    private void panel_StorylineMouseClicked(java.awt.event.MouseEvent evt) {

        if (finishedLoadingText) {
            moveDialogue();
        } else {
            finishedLoadingText = true;
            forceLoad();
        }

    }

    private void button_ConfirmActionPerformed(java.awt.event.ActionEvent evt) {

        switch (storylineIndex) {
            case 1 -> {

                textField_NameField.setVisible(false);

                player = new Player();
                quest = new Quest();
                String playerName = textField_NameField.getText().isEmpty() ? "Adventurer" : textField_NameField.getText();
                player.name = playerName;

                // puts the battlePlayer name in the characterNames hashmap
                characterNames.put(0, new String[]{playerName, "", "Player", "m"});

                nextDialogueArray();

            }
            case 2 -> {

                panel_Storyline.setVisible(false);
                panel_AffinitiesMenu.setVisible(true);
                loadText("Sanitas", button_Sanitas, false, true, TextLoadingSpeeds.SLOW.textSpeed);
                loadText("Virtus", button_Virtus, false, true, TextLoadingSpeeds.SLOW.textSpeed);
                loadText("Tutela", button_Tutela, false, true, TextLoadingSpeeds.SLOW.textSpeed);
                loadText("Madeis", button_Madeis, false, true, TextLoadingSpeeds.SLOW.textSpeed);
                loadText("Celeritas", button_Celeritas, false, true, TextLoadingSpeeds.SLOW.textSpeed);

            }
            case 3 -> {

                player.level = 1;
                player.chooseAffinity(10);

                panel_Storyline.setVisible(false);
                openAttributesMenu();
            }
            case 4 -> {
                panel_Storyline.setVisible(false);
                panel_StartingGear.setVisible(true);
                loadText("IronSword", button_IronSword, false, true, TextLoadingSpeeds.SLOW.textSpeed);
                loadText("Simple Bow", button_SimpleBow, false, true, TextLoadingSpeeds.SLOW.textSpeed);
                loadText("Crude Wand", button_CrudeWand, false, true, TextLoadingSpeeds.SLOW.textSpeed);

                // initialize the store
                store = new Store("Travelling Merchant");
                createStore("Travelling Merchant", new HashMap<String, Equipment>() {

                    {

                        put("Sword", new Weapon("Iron Sword", 1, new Skill("Slash"),
                                3, 0, 0));
                        put("Bow", new Weapon("Simple Bow", 1, new Skill("Shoot"),
                                1, 0, 2));
                        put("Wand", new Weapon("Crude Wand", 1, new Skill("Magic Missile"),
                                0, 3, 0));
                        put("Armor", new Armor("Leather Armor",
                                1, 3, 3));

                    }

                }, 1);
            }
            case 5 ->
                nextDialogueArray();
            case 6 -> {
                giveSkill(0);
            }
            case 7 -> {
                travelToLocation("Village");
                nextDialogueArray();
                panel_Storyline.setVisible(false);

                quest.newQuest(new HashMap<String, Integer[]>() {

                    {

                        put(String.format("Talk to %s in the village (found in the village).",
                                getCharacterFullName(2, true)),
                                new Integer[]{0, 1});

                    }

                }, String.format("Talk to %s",
                        getCharacterFullName(2, true)),
                        4, 3);
            }
            case 8 -> {
                nextDialogueArray();
                panel_Storyline.setVisible(false);
                generateBattle("Slime", "Madeis", 1,
                        new Weapon("Body", 1, new Skill("Tackle"), 0, 0, 0),
                        new Armor("Slime Armor", 1, 0, 2),
                        true);
                battle.escapeChance = 0;
//                messagePopup("Debug Mode");

            }
            case 9 ->
                nextDialogueArray();
            case 10 -> {
                player.fullHeal();

                travelToLocation("Village");
                openGameScreen();
                nextDialogueArray();
                panel_Storyline.setVisible(false);

                messagePopup("Quest Completed");

                quest.newQuest(new HashMap<String, Integer[]>() {

                    {

                        put(String.format("Talk to %s about your new quest (found in the village).",
                                getCharacterFullName(2, true)),
                                new Integer[]{0, 1});

                    }

                }, String.format("Talk to %s",
                        getCharacterFullName(2, true)),
                        4, 3);
            }
            case 11 -> {
                travelToLocation("Village");
                openGameScreen();
                nextDialogueArray();
                panel_Storyline.setVisible(false);

                messagePopup("Quest Completed");

                quest.newQuest(new HashMap<String, Integer[]>() {

                    {

                        put("Slime", new Integer[]{0, 3});

                    }

                }, "Kill slimes (found in the grasslands).",
                        quest.generateReward(new int[]{2}, new int[]{3})[0], quest.generateReward(new int[]{2}, new int[]{3})[1]);
            }
            case 12 -> {
                travelToLocation("Village");
                openGameScreen();
                nextDialogueArray();
                panel_Storyline.setVisible(false);

                messagePopup("Quest Completed");

                quest.newQuest(new HashMap<String, Integer[]>() {

                    {

                        put("Goblin", new Integer[]{0, 2});

                    }

                }, "Kill goblins (found in the grasslands)",
                        quest.generateReward(new int[]{4}, new int[]{2})[0], quest.generateReward(new int[]{6}, new int[]{2})[1]);
            }
            case 13 -> {
                travelToLocation("Village");
                openGameScreen();
                nextDialogueArray();
                panel_Storyline.setVisible(false);

                messagePopup("Quest Completed");

                quest.newQuest(new HashMap<String, Integer[]>() {

                    {

                        put("Wolf", new Integer[]{0, 1});

                    }

                }, "Kill a lone wolf (found in the grasslands).",
                        quest.generateReward(new int[]{6}, new int[]{1})[0], quest.generateReward(new int[]{6}, new int[]{1})[1]);
            }
            case 14 -> {
                travelToLocation("Village");
                openGameScreen();
                nextDialogueArray();
                panel_Storyline.setVisible(false);

                messagePopup("Quest Completed");

                quest.newQuest(new HashMap<String, Integer[]>() {

                    {

                        put(getCharacterFullName(7, true), new Integer[]{0, 1});

                    }

                }, String.format("Kill %s (found in the grasslands)", getCharacterFullName(7, true)),
                        quest.generateReward(new int[]{10}, new int[]{1})[0], quest.generateReward(new int[]{10}, new int[]{1})[1]);
            }
            case 15 -> // baron monologue sequence
                startBattle(true);
            default -> {
                travelToLocation(currentLocation);
            }
        }

        button_Confirm.setText("Confirm");
        button_Confirm.setVisible(false);

    }

    private void moveDialogue() {
//        System.out.println("storylineIndex: " + storylineIndex);
//        System.out.println("loadTextIndex: " + loadTextIndex);

        String[] loadedStorylineText = new String[0];

        // determine which dialogue array to load
        switch (storylineIndex) {

            case 0 -> {
                loadedStorylineText = INTRO_TUTORIAL;
            }
            case 1 -> {
                loadedStorylineText = NAME_TUTORIAL;
            }
            case 2 -> {
                loadedStorylineText = CHOOSE_CLASS_TUTORIAL;
            }
            case 3 -> {
                loadedStorylineText = EDIT_ATTRIBUTES_TUTORIAL;
            }
            case 4 -> {
                loadedStorylineText = CHOOSE_GEAR_TUTORIAL;
            }
            case 5 -> {
                loadedStorylineText = BONUS_ARMOR_TUTORIAL;
            }
            case 6 -> {
                loadedStorylineText = CHOOSESKILL_TUTORIAL;
            }
            case 7 -> {
                loadedStorylineText = VILLAGE_TUTORIAL;
            }
            case 8 -> {
                loadedStorylineText = LORE_TUTORIAL;
            }
            case 9 -> {
                if (player.currentHP < 0) {
                    loadedStorylineText = DEFEAT_TUTORIAL;
                } else {
                    loadedStorylineText = VICTORY_TUTORIAL;
                }
            }
            case 10 -> {
                loadedStorylineText = END_TUTORIAL;
            }
            case 11 -> {
                loadedStorylineText = SLIME_VILLAGE;
            }
            case 12 -> {
                loadedStorylineText = GOBLIN_VILLAGE;
            }
            case 13 -> {
                loadedStorylineText = WOLF_VILLAGE;
            }
            case 14 -> {
                loadedStorylineText = BARON_BOSS_VILLAGE;
            }
            case 15 -> {
                loadedStorylineText = BARON_BATTLE_VILLAGE;
            }

        }

//        label_StorylineText.setText(formatText(loadedStorylineText[textIndex]));
//        System.out.println("textIndex: " + textIndex);
//        System.out.println("loadedStorylineText.length: " + loadedStorylineText.length);
        if (textIndex == loadedStorylineText.length - 1) {

            if (storylineIndex == 0) {

                // picking heroine sequence
                panel_PickRescue.setVisible(true);
                loadText("Princess",
                        button_Princess, false, false, TextLoadingSpeeds.NORMAL.textSpeed);
                loadText("Prince",
                        button_Prince, false, false, TextLoadingSpeeds.NORMAL.textSpeed);

            } else if (storylineIndex == 1) {

                // entering name sequence
                textField_NameField.setVisible(true);
                loadText("Adventurer", textField_NameField, false, false, TextLoadingSpeeds.NORMAL.textSpeed);

                // entering affinity sequence
                loadText("(CONTINUE)", button_Confirm, false, false, TextLoadingSpeeds.NORMAL.textSpeed);
                button_Confirm.setVisible(true);

            }

        } else if (storylineIndex != 0 && textIndex == 0) {

            // entering affinity sequence
            button_Confirm.setText("(CONTINUE)");

            loadText("(SKIP DIALOGUE)", button_Confirm, false, false, TextLoadingSpeeds.NORMAL.textSpeed);
            button_Confirm.setVisible(true);

        }

        if (textIndex < loadedStorylineText.length) {
            loadText(storylineIndex > 1 ? formatText(loadedStorylineText[textIndex]) : loadedStorylineText[textIndex],
                    label_StorylineText, true, true, TextLoadingSpeeds.NORMAL.textSpeed);
            textIndex++;
        }

//        // moves the text index to the next line if thhe current index is not at the end of the loaded storyline text
//        if (textIndex < loadedStorylineText.length - 1) {
//            textIndex++;
//        }
    }

    public void button_PrincessActionPerformed(java.awt.event.ActionEvent evt) {

        pickRescue(PrincessPrince.PRINCESS);

    }

    public void button_PrinceActionPerformed(java.awt.event.ActionEvent evt) {

        pickRescue(PrincessPrince.PRINCE);

    }

    public void pickRescue(PrincessPrince rescue) {

        generateNPCNames(rescue);
        panel_PickRescue.setVisible(false);
        nextDialogueArray();

    }

    private void nextDialogueArray() {

        panel_Storyline.setVisible(true);
        storylineIndex++;
        textIndex = 0;

        loadText("[CLICK TO START]", label_StorylineText, true, false, TextLoadingSpeeds.NORMAL.textSpeed);

    }

    private void speakToNPC(int npcIndex) {

        hideScreens();
        panel_Storyline.setVisible(true);

        if (npcIndex >= 0) {

            loadText(String.format("%s %s",
                    characterNames.get(npcIndex)[0], // first name
                    characterNames.get(npcIndex)[1] // last name
            ), label_Talker, false, false, TextLoadingSpeeds.NORMAL.textSpeed);
            loadText(characterNames.get(npcIndex)[2], label_Title, false, false, TextLoadingSpeeds.NORMAL.textSpeed);

        } else {

            loadText("???",
                    label_Talker, false, false, TextLoadingSpeeds.NORMAL.textSpeed);
            loadText("???",
                    label_Title, false, false, TextLoadingSpeeds.NORMAL.textSpeed);

        }

        loadText("[CLICK TO START]", label_StorylineText, true, false, TextLoadingSpeeds.NORMAL.textSpeed);

        button_Confirm.setVisible(false);

        textIndex = 0;

    }

    private void forceLoad() {

        for (int i = 0; i < loadingInstances.size(); i++) {

            loadingInstances.get(i).forceCancel();

        }

        finishedLoadingText = true;

    }

    public static void loadText(
            String givenText, JComponent targetComponent, boolean loadedRequired, boolean formatText, int givenSpeed) {

        if (loadedRequired) {
            finishedLoadingText = false;
        }

        loadingInstances.add(new LoadingText(givenText, targetComponent, formatText, givenSpeed));

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="character names stuff">
    private String formatText(String textToBeFormatted) {

        for (int characterIndex = 0; characterIndex < charactersMap.size(); characterIndex++) {

            if (textToBeFormatted.contains(charactersMap.get(characterIndex))) {
                String fullName = characterIndex != 0 ? getCharacterFullName(characterIndex, true) : getCharacterFullName(characterIndex, false);
                textToBeFormatted = textToBeFormatted.replace(charactersMap.get(characterIndex), fullName);
            }

        }

        return textToBeFormatted;

    }

    private String getCharacterFullName(int givenCharacterIndex, boolean includeTitle) {

        String fullNameStr = includeTitle == true ? String.format("%s %s %s",
                characterNames.get(givenCharacterIndex)[2],
                characterNames.get(givenCharacterIndex)[0],
                characterNames.get(givenCharacterIndex)[1])
                : String.format("%s %s",
                        characterNames.get(givenCharacterIndex)[0],
                        characterNames.get(givenCharacterIndex)[1]);

        return fullNameStr;

    }

    private void generateNPCNames(PrincessPrince gender) {

        Random nameRandomizer = new Random();

        /*
            battlePlayer = 0
            princess = 1
            village elder = 2
            lord/ lady = 3
            duke/ duchess = 4
            commander = 5
            king/ queen = 6
            baroness/ baron = 7
            general = 8
            lesser lord/ lady = 9
            arch demon/ demoness = 10
            prince/ princess of the underworld = 11
         */
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="Array lists of names">
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="princess first names">
        ArrayList<String> listOfPrincessFirstNames = new ArrayList<>();
        listOfPrincessFirstNames.add("Evelyn");
        listOfPrincessFirstNames.add("Rosamont");
        listOfPrincessFirstNames.add("Elowen");
        listOfPrincessFirstNames.add("Cecilia");
        listOfPrincessFirstNames.add("Gwendolyn");
        listOfPrincessFirstNames.add("Leonora");
        listOfPrincessFirstNames.add("Theodora");
        listOfPrincessFirstNames.add("Marianne");
        listOfPrincessFirstNames.add("Beatrix");
        listOfPrincessFirstNames.add("Emilia");
        listOfPrincessFirstNames.add("Lorelei");
        listOfPrincessFirstNames.add("Anneliese");
        listOfPrincessFirstNames.add("Eléonore");
        listOfPrincessFirstNames.add("Carmilla");
        listOfPrincessFirstNames.add("Genevieve");
        listOfPrincessFirstNames.add("Celestina");
        listOfPrincessFirstNames.add("Evangeline");
        listOfPrincessFirstNames.add("Giselle");
        listOfPrincessFirstNames.add("Lysandra");
        listOfPrincessFirstNames.add("Delphina");
        listOfPrincessFirstNames.add("Esmeralda");
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="princess first names">
        ArrayList<String> listOfPrinceFirstNames = new ArrayList<>();
        listOfPrinceFirstNames.add("William");
        listOfPrinceFirstNames.add("Charles");
        listOfPrinceFirstNames.add("Alexander");
        listOfPrinceFirstNames.add("Arthur");
        listOfPrinceFirstNames.add("Richard");
        listOfPrinceFirstNames.add("Adrian");
        listOfPrinceFirstNames.add("Theodore");
        listOfPrinceFirstNames.add("Goldwyn");
        listOfPrinceFirstNames.add("Aurelius");
        listOfPrinceFirstNames.add("Griffith");
        listOfPrinceFirstNames.add("Charles");
        listOfPrinceFirstNames.add("Sebastian");
        listOfPrinceFirstNames.add("Desmond");
        listOfPrinceFirstNames.add("Wulfric");
        listOfPrinceFirstNames.add("Edward");
        listOfPrinceFirstNames.add("Maxwell");
        listOfPrinceFirstNames.add("Benjamin");
        listOfPrinceFirstNames.add("Edmund");
        listOfPrinceFirstNames.add("Winston");
        listOfPrinceFirstNames.add("Pierre");
        listOfPrinceFirstNames.add("Oswald");
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="royalty first names">
        ArrayList<String[]> listOfRoyalFirstNames = new ArrayList<>();
        listOfRoyalFirstNames.add(new String[]{"William", "m"});
        listOfRoyalFirstNames.add(new String[]{"Charles", "m"});
        listOfRoyalFirstNames.add(new String[]{"George", "m"});
        listOfRoyalFirstNames.add(new String[]{"Connor", "m"});
        listOfRoyalFirstNames.add(new String[]{"Nicholas", "m"});
        listOfRoyalFirstNames.add(new String[]{"Wulfric", "m"});
        listOfRoyalFirstNames.add(new String[]{"Bartholomew", "m"});
        listOfRoyalFirstNames.add(new String[]{"Benedict", "m"});
        listOfRoyalFirstNames.add(new String[]{"Alexander", "m"});
        listOfRoyalFirstNames.add(new String[]{"Alaric", "m"});
        listOfRoyalFirstNames.add(new String[]{"Hadrian", "m"});
        listOfRoyalFirstNames.add(new String[]{"Thelric", "m"});
        listOfRoyalFirstNames.add(new String[]{"Lucas", "m"});
        listOfRoyalFirstNames.add(new String[]{"Sebastian", "m"});
        listOfRoyalFirstNames.add(new String[]{"Arthur", "m"});
        listOfRoyalFirstNames.add(new String[]{"Seraphina", "f"});
        listOfRoyalFirstNames.add(new String[]{"Maerith", "f"});
        listOfRoyalFirstNames.add(new String[]{"Adelindra", "f"});
        listOfRoyalFirstNames.add(new String[]{"Alinora", "f"});
        listOfRoyalFirstNames.add(new String[]{"Eveline", "f"});
        listOfRoyalFirstNames.add(new String[]{"Melaina", "f"});
        listOfRoyalFirstNames.add(new String[]{"Marigold", "f"});
        listOfRoyalFirstNames.add(new String[]{"Miranda", "f"});
        listOfRoyalFirstNames.add(new String[]{"Katharina", "f"});
        listOfRoyalFirstNames.add(new String[]{"Ophelia", "f"});
        listOfRoyalFirstNames.add(new String[]{"Beatrice", "f"});
        listOfRoyalFirstNames.add(new String[]{"Rosalina", "f"});
        listOfRoyalFirstNames.add(new String[]{"Isabella", "f"});
        listOfRoyalFirstNames.add(new String[]{"Elizabeth", "f"});
        listOfRoyalFirstNames.add(new String[]{"Margaret", "f"});
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="royalty surnames">
        ArrayList<String> listOfRoyalSurames = new ArrayList<>();
        listOfRoyalSurames.add("Valenfort");
        listOfRoyalSurames.add("Lionhart");
        listOfRoyalSurames.add("Alderwynd");
        listOfRoyalSurames.add("Kaisereich");
        listOfRoyalSurames.add("Eichenwald");
        listOfRoyalSurames.add("Grimsburg");
        listOfRoyalSurames.add("Eastershire");
        listOfRoyalSurames.add("Ravenwell");
        listOfRoyalSurames.add("Hearthsvale");
        listOfRoyalSurames.add("Drachtenfeld");
        listOfRoyalSurames.add("Falkenford");
        listOfRoyalSurames.add("Shwarzeholdt");
        listOfRoyalSurames.add("Himmelfurt");
        listOfRoyalSurames.add("Montrevaux");
        listOfRoyalSurames.add("Duclaret");
        listOfRoyalSurames.add("Wolfsheim");
        listOfRoyalSurames.add("Steinwulf");
        listOfRoyalSurames.add("Montclaire");
        listOfRoyalSurames.add("Clermontaine");
        listOfRoyalSurames.add("Florandis");
        listOfRoyalSurames.add("Belleroix");
        listOfRoyalSurames.add("Clermontaine");
        listOfRoyalSurames.add("Rousselique");
        listOfRoyalSurames.add("Laurevigne");
        listOfRoyalSurames.add("Duvallon");
        listOfRoyalSurames.add("Carmichael");
        listOfRoyalSurames.add("Bradfort");
        listOfRoyalSurames.add("Bonavich");
        listOfRoyalSurames.add("Harrington");
        listOfRoyalSurames.add("Barkshire");
        listOfRoyalSurames.add("Aldereich");
        listOfRoyalSurames.add("Astor");
        listOfRoyalSurames.add("Lorraine");
        listOfRoyalSurames.add("Ellington");
        listOfRoyalSurames.add("Castleton");
        listOfRoyalSurames.add("Davenport");
        listOfRoyalSurames.add("Delacroix");
        listOfRoyalSurames.add("Havilland");
        listOfRoyalSurames.add("Greenwood");
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="demonic first names">
        ArrayList<String[]> listOfDemonicFirstNames = new ArrayList<>();
        listOfDemonicFirstNames.add(new String[]{"Setaroth", "m"});
        listOfDemonicFirstNames.add(new String[]{"Satanir", "m"});
        listOfDemonicFirstNames.add(new String[]{"Maddon", "m"});
        listOfDemonicFirstNames.add(new String[]{"Belphegon", "m"});
        listOfDemonicFirstNames.add(new String[]{"Asmodenus", "m"});
        listOfDemonicFirstNames.add(new String[]{"Beelzus", "m"});
        listOfDemonicFirstNames.add(new String[]{"Remiael", "m"});
        listOfDemonicFirstNames.add(new String[]{"Begemoth", "m"});
        listOfDemonicFirstNames.add(new String[]{"Baeloth", "m"});
        listOfDemonicFirstNames.add(new String[]{"Vareximon", "m"});
        listOfDemonicFirstNames.add(new String[]{"Obrithiel", "m"});
        listOfDemonicFirstNames.add(new String[]{"Zekarion", "m"});
        listOfDemonicFirstNames.add(new String[]{"Samaqel", "m"});
        listOfDemonicFirstNames.add(new String[]{"Belzahir", "m"});
        listOfDemonicFirstNames.add(new String[]{"Azrakael", "m"});

        listOfDemonicFirstNames.add(new String[]{"Malgrith", "f"});
        listOfDemonicFirstNames.add(new String[]{"Loravael", "f"});
        listOfDemonicFirstNames.add(new String[]{"Lucifera", "f"});
        listOfDemonicFirstNames.add(new String[]{"Lilith", "f"});
        listOfDemonicFirstNames.add(new String[]{"Nameenah", "f"});
        listOfDemonicFirstNames.add(new String[]{"Ashtera", "f"});
        listOfDemonicFirstNames.add(new String[]{"Uriela", "f"});
        listOfDemonicFirstNames.add(new String[]{"Zahreh", "f"});
        listOfDemonicFirstNames.add(new String[]{"Belphevra", "f"});
        listOfDemonicFirstNames.add(new String[]{"Drevaelah", "f"});
        listOfDemonicFirstNames.add(new String[]{"Malithra", "f"});
        listOfDemonicFirstNames.add(new String[]{"Ezkireth", "f"});
        listOfDemonicFirstNames.add(new String[]{"Ysmar", "f"});
        listOfDemonicFirstNames.add(new String[]{"Helviatha", "f"});
        listOfDemonicFirstNames.add(new String[]{"Ophiriel", "f"});
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="demonic surnames">
        ArrayList<String> listOfDemonicSurnames = new ArrayList<>();
        listOfDemonicSurnames.add("Blackthorn");
        listOfDemonicSurnames.add("Shadowmere");
        listOfDemonicSurnames.add("Grimshaw");
        listOfDemonicSurnames.add("Darkweaver");
        listOfDemonicSurnames.add("Hellstrom");
        listOfDemonicSurnames.add("Nightshade");
        listOfDemonicSurnames.add("Dreadmore");
        listOfDemonicSurnames.add("Gravecourt");
        listOfDemonicSurnames.add("Harrowfell");
        listOfDemonicSurnames.add("Crimsonreach");
        listOfDemonicSurnames.add("Blutkreig");
        listOfDemonicSurnames.add("Hexenwaldt");
        listOfDemonicSurnames.add("Eisenwraith");
        listOfDemonicSurnames.add("Malrevoir");
        listOfDemonicSurnames.add("Charnoire");
        listOfDemonicSurnames.add("Sangversé");
        listOfDemonicSurnames.add("Bellombre");
        listOfDemonicSurnames.add("Vaulremort");
        listOfDemonicSurnames.add("Infernus");
        listOfDemonicSurnames.add("Malachai");
        listOfDemonicSurnames.add("Grimharrow");
        listOfDemonicSurnames.add("Nightbramble");
        listOfDemonicSurnames.add("Ruinmarsh");
        listOfDemonicSurnames.add("Kriegfaust");
        listOfDemonicSurnames.add("Blutnacht");
        listOfDemonicSurnames.add("Flammenriss");
        listOfDemonicSurnames.add("Totensee");
        listOfDemonicSurnames.add("Noireclat");
        listOfDemonicSurnames.add("Vallombreux");
        listOfDemonicSurnames.add("Belleschain");
        listOfDemonicSurnames.add("Faucheval");
        listOfDemonicSurnames.add("Morvelain");
        listOfDemonicSurnames.add("Revenoir");
        listOfDemonicSurnames.add("Darkenveil");
        listOfDemonicSurnames.add("Hellhammer");
        listOfDemonicSurnames.add("Hellscream");
        listOfDemonicSurnames.add("Hexmourne");
        listOfDemonicSurnames.add("Umbramist");
        listOfDemonicSurnames.add("Gloomwraith");
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="generates npc names">
        int randomizedPrincessName = gender == PrincessPrince.PRINCESS
                ? nameRandomizer.nextInt(listOfPrincessFirstNames.size())
                : nameRandomizer.nextInt(listOfPrinceFirstNames.size());
        int randomizedLastName = nameRandomizer.nextInt(listOfRoyalSurames.size());
        characterNames.put(1,
                new String[]{
                    gender == PrincessPrince.PRINCESS
                            ? listOfPrincessFirstNames.get(randomizedPrincessName)
                            : listOfPrinceFirstNames.get(randomizedPrincessName),
                    listOfRoyalSurames.remove(randomizedLastName),
                    gender == PrincessPrince.PRINCESS
                            ? "Princess" : "Prince",
                    gender == PrincessPrince.PRINCESS
                            ? "f" : "m"});

        int randomizedFirstName = nameRandomizer.nextInt(listOfRoyalFirstNames.size());
        randomizedLastName = nameRandomizer.nextInt(listOfRoyalSurames.size());
        characterNames.put(2,
                new String[]{listOfRoyalFirstNames.get(randomizedFirstName)[0],
                    listOfRoyalSurames.remove(randomizedLastName),
                    "Village Elder",
                    listOfRoyalFirstNames.remove(randomizedFirstName)[1]});

        // lord lady
        randomizedFirstName = nameRandomizer.nextInt(listOfRoyalFirstNames.size());
        randomizedLastName = nameRandomizer.nextInt(listOfRoyalSurames.size());
        characterNames.put(3,
                new String[]{listOfRoyalFirstNames.get(randomizedFirstName)[0],
                    listOfRoyalSurames.remove(randomizedLastName),
                    listOfRoyalFirstNames.get(randomizedFirstName)[1].equals("f") ? "Lady" : "Lord",
                    listOfRoyalFirstNames.remove(randomizedFirstName)[1]});

        randomizedFirstName = nameRandomizer.nextInt(listOfRoyalFirstNames.size());
        randomizedLastName = nameRandomizer.nextInt(listOfRoyalSurames.size());
        characterNames.put(4,
                new String[]{listOfRoyalFirstNames.get(randomizedFirstName)[0],
                    listOfRoyalSurames.remove(randomizedLastName),
                    listOfRoyalFirstNames.get(randomizedFirstName)[1].equals("f") ? "Duchess" : "Duke",
                    listOfRoyalFirstNames.remove(randomizedFirstName)[1]});

        randomizedFirstName = nameRandomizer.nextInt(listOfRoyalFirstNames.size());
        randomizedLastName = nameRandomizer.nextInt(listOfRoyalSurames.size());
        characterNames.put(5,
                new String[]{listOfRoyalFirstNames.get(randomizedFirstName)[0],
                    listOfRoyalSurames.remove(randomizedLastName),
                    "Commander",
                    listOfRoyalFirstNames.remove(randomizedFirstName)[1]});

        randomizedFirstName = nameRandomizer.nextInt(listOfRoyalFirstNames.size());
        randomizedLastName = nameRandomizer.nextInt(listOfRoyalSurames.size());
        characterNames.put(6,
                new String[]{listOfRoyalFirstNames.get(randomizedFirstName)[0],
                    listOfRoyalSurames.remove(randomizedLastName),
                    listOfRoyalFirstNames.get(randomizedFirstName)[1].equals("f") ? "Queen" : "King",
                    listOfRoyalFirstNames.remove(randomizedFirstName)[1]});
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------------------------
        // <editor-fold desc="generates boss names">
        int randomizedDemonFirstName = nameRandomizer.nextInt(listOfDemonicFirstNames.size());
        int randomizedDemonLastName = nameRandomizer.nextInt(listOfDemonicSurnames.size());
        characterNames.put(7,
                new String[]{listOfDemonicFirstNames.get(randomizedDemonFirstName)[0],
                    listOfDemonicSurnames.remove(randomizedDemonLastName),
                    listOfDemonicFirstNames.get(randomizedDemonFirstName)[1].equals("f") ? "Baroness" : "Baron",
                    listOfDemonicFirstNames.remove(randomizedDemonFirstName)[1]});

        randomizedDemonFirstName = nameRandomizer.nextInt(listOfDemonicFirstNames.size());
        randomizedDemonLastName = nameRandomizer.nextInt(listOfDemonicSurnames.size());
        characterNames.put(8,
                new String[]{listOfDemonicFirstNames.get(randomizedDemonFirstName)[0],
                    listOfDemonicSurnames.remove(randomizedDemonLastName),
                    "General",
                    listOfDemonicFirstNames.remove(randomizedDemonFirstName)[1]});

        randomizedDemonFirstName = nameRandomizer.nextInt(listOfDemonicFirstNames.size());
        randomizedDemonLastName = nameRandomizer.nextInt(listOfDemonicSurnames.size());
        characterNames.put(9,
                new String[]{listOfDemonicFirstNames.get(randomizedDemonFirstName)[0],
                    listOfDemonicSurnames.remove(randomizedDemonLastName),
                    listOfDemonicFirstNames.get(randomizedDemonFirstName)[1].equals("f") ? "Lesser Lady" : "Lesser Lord",
                    listOfDemonicFirstNames.remove(randomizedDemonFirstName)[1]});

        randomizedDemonFirstName = nameRandomizer.nextInt(listOfDemonicFirstNames.size());
        randomizedDemonLastName = nameRandomizer.nextInt(listOfDemonicSurnames.size());
        characterNames.put(10,
                new String[]{listOfDemonicFirstNames.get(randomizedDemonFirstName)[0],
                    listOfDemonicSurnames.remove(randomizedDemonLastName),
                    listOfDemonicFirstNames.get(randomizedDemonFirstName)[1].equals("f") ? "Arch Demoness" : "Arch Demon",
                    listOfDemonicFirstNames.remove(randomizedDemonFirstName)[1]});

        randomizedDemonFirstName = nameRandomizer.nextInt(listOfDemonicFirstNames.size());
        randomizedDemonLastName = nameRandomizer.nextInt(listOfDemonicSurnames.size());
        characterNames.put(11,
                new String[]{listOfDemonicFirstNames.get(randomizedDemonFirstName)[0],
                    listOfDemonicSurnames.remove(randomizedDemonLastName),
                    listOfDemonicFirstNames.get(randomizedDemonFirstName)[1].equals("f") ? "Princess of the Underworld" : "Prince of the Underworld",
                    listOfDemonicFirstNames.remove(randomizedDemonFirstName)[1]});
        // </editor-fold>
        // ------------------------------------------------------------------------------------------------------------

    }
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="choosing your affinity stuff">

    private void button_VirtusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_VirtusActionPerformed

        setPlayerClass("Virtus");

    }//GEN-LAST:event_button_VirtusActionPerformed

    private void button_MadeisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_MadeisActionPerformed

        setPlayerClass("Madeis");

    }//GEN-LAST:event_button_MadeisActionPerformed

    private void button_CeleritasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CeleritasActionPerformed

        setPlayerClass("Celeritas");


    }//GEN-LAST:event_button_CeleritasActionPerformed

    private void button_SanitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SanitasActionPerformed

        setPlayerClass("Sanitas");

    }//GEN-LAST:event_button_SanitasActionPerformed

    private void button_TutelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_TutelaActionPerformed

        setPlayerClass("Tutela");

    }//GEN-LAST:event_button_TutelaActionPerformed

    private void setPlayerClass(String playerClass) {

        player.typeAffinity = playerClass;
        panel_AffinitiesMenu.setVisible(false);
        nextDialogueArray();

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="status stuff">
    private void openAttributesMenu() {

        panel_Status.setVisible(true);

        loadText(player.name,
                label_PlayerName, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText(player.typeAffinity,
                label_PlayerAffinity, false, true, TextLoadingSpeeds.NORMAL.textSpeed);

        loadText(String.format("Level: %s (%.2f / %.2f)\n", String.valueOf(player.level),
                player.xp, player.xpNeeded),
                label_Level, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText(String.format("Available Attributes Points: %s\n", String.valueOf(player.attributePoints)),
                label_AvailablePoints, false, true, TextLoadingSpeeds.NORMAL.textSpeed);

        loadText(String.valueOf(player.healthPoints),
                label_TotalHP, false, true, TextLoadingSpeeds.FAST.textSpeed);
        loadText(String.valueOf(player.strengthPoints),
                label_TotalSP, false, true, TextLoadingSpeeds.FAST.textSpeed);
        loadText(String.valueOf(player.defensePoints),
                label_TotalDP, false, true, TextLoadingSpeeds.FAST.textSpeed);
        loadText(String.valueOf(player.intelligencePoints),
                label_TotalIP, false, true, TextLoadingSpeeds.FAST.textSpeed);
        loadText(String.valueOf(player.agilityPoints),
                label_TotalAP, false, true, TextLoadingSpeeds.FAST.textSpeed);

        loadText("Health Points (HP): ",
                label_HealthPoints, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText("Strength Points (SP): ",
                label_StrengthPoints, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText("Defense Points (DP): ",
                label_DefensePoints, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText("Intelligence Points (IP): ",
                label_IntelligencePoints, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText("Agility Points (AP): ",
                label_AgilityPoints, false, true, TextLoadingSpeeds.NORMAL.textSpeed);

        showGearAdditions();

        resetAttributeChanges();

        showAvailableAttributePoints();

    }

    private void showGearAdditions() {

        updateGearAddition(label_GearHP, player.HPGearAddition);
        updateGearAddition(label_GearSP, player.SPGearAddition);
        updateGearAddition(label_GearDP, player.DPGearAddition);
        updateGearAddition(label_GearIP, player.IPGearAddition);
        updateGearAddition(label_GearAP, player.APGearAddition);

    }

    private void updateGearAddition(JLabel label, double value) {

        if (value > 0) {
            loadText(String.format("(+%s)", value),
                    label, false, true, TextLoadingSpeeds.FAST.textSpeed);
            label.setVisible(true);
        } else {
            label.setVisible(false);
        }

    }

    // <editor-fold desc="addition buttons">
    private void button_HPAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_HPAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Sanitas");
        }

    }//GEN-LAST:event_button_HPAdditionActionPerformed

    private void button_APAdditionAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_APAdditionAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Celeritas");
        }

    }//GEN-LAST:event_button_APAdditionAdditionActionPerformed

    private void button_IPAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_IPAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Madeis");
        }

    }//GEN-LAST:event_button_IPAdditionActionPerformed

    private void button_DPAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DPAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Tutela");
        }

    }//GEN-LAST:event_button_DPAdditionActionPerformed

    private void button_SPAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SPAdditionActionPerformed

        if (player.attributePoints > 0) {
            attributeAddition("Virtus");
        }

    }//GEN-LAST:event_button_SPAdditionActionPerformed

    private void button_AttributesConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AttributesConfirmActionPerformed

        player.confirmAttributeChanges();

        openAttributesMenu();
        setAttributesAddition();

        if (player.attributePoints < 1) {
            panel_AttributesActions.setVisible(false);
        }

        if (storylineIndex == 3) {

            panel_Status.setVisible(false);

            nextDialogueArray();
        }

    }//GEN-LAST:event_button_AttributesConfirmActionPerformed

    private void button_AttributesResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_AttributesResetActionPerformed

        resetAttributeChanges();
        openAttributesMenu();

    }//GEN-LAST:event_button_AttributesResetActionPerformed
    // </editor-fold>

    private void resetAttributeChanges() {

        label_HPAddition.setText("");
        label_APAddition.setText("");
        label_IPAddition.setText("");
        label_DPAddition.setText("");
        label_SPAddition.setText("");

        player.resetAttributeChanges();
    }

    // opens the attributes menu
    private void button_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_StatusActionPerformed

        panel_Game.setVisible(false);
        showReturn();
        openAttributesMenu();

    }//GEN-LAST:event_button_StatusActionPerformed

    // return to game menu (appears in travel, inventory, and attributes/ status menu)
    private void button_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ReturnActionPerformed

        travelToLocation(currentLocation);

    }//GEN-LAST:event_button_ReturnActionPerformed

    // adds an attribute point to the selected attribute (given as a String parameter)
    private void attributeAddition(String attribute) {

        player.attributePoints--;

        player.attributeAddition(attribute);

        setAttributesAddition();

        showAvailableAttributePoints();

    }

    // finalizes the attributes added once confirmed has been selected
    private void setAttributesAddition() {
        panel_Status.setVisible(true);
        label_AvailablePoints.setText(String.format("Available Attributes Points: %s\n", String.valueOf(player.attributePoints)));

        panel_AttributesAddition.setVisible(true);

        if (player.HPAddition > 0) {
            label_HPAddition.setText(String.format("+%s", String.valueOf(player.HPAddition)));
            label_HPAddition.setVisible(true);
        } else {
            label_HPAddition.setVisible(false);
        }
        if (player.APAddition > 0) {
            label_APAddition.setText(String.format("+%s", String.valueOf(player.APAddition)));
            label_APAddition.setVisible(true);
        } else {
            label_APAddition.setVisible(false);
        }
        if (player.IPAddition > 0) {
            label_IPAddition.setText(String.format("+%s", String.valueOf(player.IPAddition)));
            label_IPAddition.setVisible(true);
        } else {
            label_IPAddition.setVisible(false);
        }
        if (player.DPAddition > 0) {
            label_DPAddition.setText(String.format("+%s", String.valueOf(player.DPAddition)));
            label_DPAddition.setVisible(true);
        } else {
            label_DPAddition.setVisible(false);
        }
        if (player.SPAddition > 0) {
            label_SPAddition.setText(String.format("+%s", String.valueOf(player.SPAddition)));
            label_SPAddition.setVisible(true);
        } else {
            label_SPAddition.setVisible(false);
        }

        showAvailableAttributePoints();
    }

    public void showAvailableAttributePoints() {

        if (player.attributePoints > 0) {

            panel_AttributesActions.setVisible(true);
            loadText("Reset",
                    button_AttributesReset, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
            loadText("Confirm",
                    button_AttributesConfirm, false, true, TextLoadingSpeeds.NORMAL.textSpeed);

            panel_AttributesAdditionButtons.setVisible(true);
            panel_AttributesAddition.setVisible(true);

        } else if (player.HPAddition > 0
                || player.APAddition > 0
                || player.IPAddition > 0
                || player.DPAddition > 0
                || player.SPAddition > 0) {

            panel_AttributesAdditionButtons.setVisible(false);

        } else {

            panel_AttributesActions.setVisible(false);
            panel_AttributesAdditionButtons.setVisible(false);

        }
    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="choosing the starting gear and inventory stuff">

    private void button_IronSwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_IronSwordActionPerformed

        setPlayerStartingWeapon("Iron Sword");

    }//GEN-LAST:event_button_IronSwordActionPerformed

    private void button_SimpleBowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SimpleBowActionPerformed

        setPlayerStartingWeapon("Simple Bow");

    }//GEN-LAST:event_button_SimpleBowActionPerformed

    private void button_CrudeWandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CrudeWandActionPerformed

        setPlayerStartingWeapon("Crude Wand");

    }//GEN-LAST:event_button_CrudeWandActionPerformed

    private void button_InventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_InventoryActionPerformed

        panel_Game.setVisible(false);
        showReturn();
        openInventory();

    }//GEN-LAST:event_button_InventoryActionPerformed

    private void openInventory() {

        panel_Skills.setVisible(false);
        panel_Inventory.setVisible(true);

        loadText(String.format("Equipped Armor: %s (LVL %s)", player.equippedArmor.equipmentName, player.equippedArmor.equipmentLVL),
                label_Armor, false, true, TextLoadingSpeeds.SLOW.textSpeed);
        loadText(String.valueOf(player.physicalDefense),
                label_TotalPDef, false, true, TextLoadingSpeeds.FAST.textSpeed);
        loadText(String.valueOf(player.magicalDefense),
                label_TotalMDef, false, true, TextLoadingSpeeds.FAST.textSpeed);

        loadText(String.format("Equipped Weapon: %s (LVL %s)", player.equippedWeapon.equipmentName, player.equippedWeapon.equipmentLVL),
                label_Weapon, false, true, TextLoadingSpeeds.SLOW.textSpeed);
        loadText(String.valueOf(player.physicalDamage),
                label_TotalPDmg, false, true, TextLoadingSpeeds.FAST.textSpeed);
        loadText(String.valueOf(player.magicalDamage),
                label_TotalMDmg, false, true, TextLoadingSpeeds.FAST.textSpeed);
        loadText(String.valueOf(player.critChance),
                label_TotalCC, false, true, TextLoadingSpeeds.FAST.textSpeed);

        loadText("Physical Defence (PDef): ",
                label_PDef, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText("Magical Defense (MDef): ",
                label_MDef, false, true, TextLoadingSpeeds.NORMAL.textSpeed);

        loadText("Physical Damage (PDmg): ",
                label_PDmg, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText("Magical Damage (MDmg): ",
                label_MDmg, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText("Critical Chance (CC): ",
                label_CC, false, true, TextLoadingSpeeds.NORMAL.textSpeed);

        updateGearLabel(label_GearPDef, player.pDefGearAddition);
        updateGearLabel(label_GearMDef, player.mDefGearAddition);
        updateGearLabel(label_GearPDmg, player.pDmgGearAddition);
        updateGearLabel(label_GearMDmg, player.mDmgGearAddition);
        updateGearLabel(label_GearCC, player.cCGearAddition);

        loadSkillButton();

    }

    private void updateGearLabel(JLabel label, double value) {

        if (value > 0) {
            loadText(String.format("(+%s)", value),
                    label, false, true, TextLoadingSpeeds.FAST.textSpeed);
            label.setVisible(true);
        } else {
            label.setVisible(false);
        }

    }

    private void setPlayerStartingWeapon(String chosenWeapon) {

        Armor startingArmor = new Armor("Leather Armor", 1, 3, 3);
        store.purchaseMerchandise("Travelling Merchant", "Armor");

        Skill startingWeaponBasicAttackSkill = new Skill("");
        int startingWeaponSP = 0;
        int startingWeaponIP = 0;
        int startingWeaponAP = 0;

        switch (chosenWeapon) {
            case "Iron Sword" -> {
                startingWeaponSP = 3;
                startingWeaponBasicAttackSkill = new Skill("Slash");
                store.purchaseMerchandise("Travelling Merchant", "Sword");
            }
            case "Simple Bow" -> {
                startingWeaponSP = 1;
                startingWeaponAP = 2;
                startingWeaponBasicAttackSkill = new Skill("Shoot");
                store.purchaseMerchandise("Travelling Merchant", "Bow");
            }
            case "Crude Wand" -> {
                startingWeaponIP = 3;
                startingWeaponBasicAttackSkill = new Skill("Magic Missile");
                store.purchaseMerchandise("Travelling Merchant", "Wand");
            }
        }

        Weapon startingWeapon = new Weapon(chosenWeapon, 1, startingWeaponBasicAttackSkill,
                startingWeaponSP, startingWeaponIP, startingWeaponAP);

        player.equipGear(startingWeapon, startingArmor);
        panel_StartingGear.setVisible(false);
        nextDialogueArray();

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="travelling and location buttons stuff">
    private void button_Place3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Place3ActionPerformed

        placeButtonAction("place3");

    }//GEN-LAST:event_button_Place3ActionPerformed

    private void button_Place2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Place2ActionPerformed

        placeButtonAction("place2");

    }//GEN-LAST:event_button_Place2ActionPerformed

    private void button_Place1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_Place1ActionPerformed

        placeButtonAction("place1");

    }//GEN-LAST:event_button_Place1ActionPerformed

    private void placeButtonAction(String placeActionType) {

        switch (placeActionType) {

            case "place1" -> {

                if (currentLocation.equals("Village")) {

                    if (storylineIndex <= 6) {

                        speakToNPC(2);

                    } else if (storylineIndex <= 9) {

                        speakToNPC(2);

                    } else if (storylineIndex <= 10) {

                        if (quest.isQuestCompleted()) {

                            speakToNPC(2);

                        } else {

                            messagePopup("Quest Incomplete");

                        }

                    } else if (storylineIndex <= 11) {

                        if (quest.isQuestCompleted()) {

                            speakToNPC(2);

                        } else {

                            messagePopup("Quest Incomplete");

                        }

                    } else if (storylineIndex <= 12) {

                        if (quest.isQuestCompleted()) {

                            speakToNPC(2);

                        } else {

                            messagePopup("Quest Incomplete");

                        }

                    } else if (storylineIndex <= 13) {

                        if (quest.isQuestCompleted()) {

                            speakToNPC(2);

                        } else {

                            messagePopup("Quest Incomplete");

                        }

                    } else if (storylineIndex <= 14) {

                        if (quest.isQuestCompleted()) {

                            speakToNPC(2);

                        } else {

                            messagePopup("Quest Incomplete");

                        }

                    }

                } else if (currentLocation.equals("Grasslands")) {

                    // explore dungeon
                    exploreDungeon(currentLocation, true);

                }

            }
            case "place2" -> {

                if (currentLocation.equals("Village")) {

                    if (storylineIndex == 6) {

                        messagePopup("Quest Incomplete");

                    } else if (storylineIndex >= 9) {

                        enterStore("Travelling Merchant");

                    }

                } else if (currentLocation.equals("Grasslands")) {

                    // explore dungeon
                    invadingBossLair = true;
                    exploreDungeon(currentLocation, true);

                }

            }
            case "place3" -> {

                if (currentLocation.equals("Village")) {
                    if (player.currentHP < player.maxHP
                            || player.currentMP < player.maxMP) {

                        if (currentLocation.equals("Village")) {

                            returnHome("Home");

                        }

                    } else {

                        messagePopup("Full HP and MP");

                    }

                }

            }

        }

    }

    private void returnHome(String restType) {

        player.fullHeal();

        travelToLocation("Village");

        panel_Game.setVisible(false);
        panel_Home.setVisible(true);

        String homeLabelStr = "";

        switch (restType) {

            case "Home" -> {
                homeLabelStr = "You rested peacefully in your home.";
            }
            case "Defeated" -> {
                homeLabelStr = "You were foundly barely alive and brought to your home. You rested for a long time to recuperate your injuries.";
            }

        }

        warningsMap.remove("Full HP and MP");
        warningsMap.remove("Low HP");
        warningsMap.remove("Low MP");

        loadText(homeLabelStr,
                label_HomeLabel, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText("(CLICK TO RETURN)",
                label_HomeReturn, false, true, TextLoadingSpeeds.NORMAL.textSpeed);

    }

    private void panel_HomeMouseClicked(java.awt.event.MouseEvent evt) {

        travelToLocation("Village");

    }

    private void travelToLocation(String locationTravelledTo) {

        button_Place1.setVisible(false);
        button_Place2.setVisible(false);
        button_Place3.setVisible(false);

        ImageIcon ii = new ImageIcon();
        // put the locations here
        switch (locationTravelledTo) {
            case "Village" -> {
                ii = new ImageIcon(getClass().getResource("/realmofallyria/images/village.png"));

                loadText("Village Elder",
                        button_Place1, false, true, TextLoadingSpeeds.SLOW.textSpeed);
                button_Place1.setVisible(true);

                if (storylineIndex > 9) {
                    loadText("Travelling Merchant",
                            button_Place2, false, true, TextLoadingSpeeds.SLOW.textSpeed);
                    button_Place2.setVisible(true);
                    loadText("Home",
                            button_Place3, false, true, TextLoadingSpeeds.SLOW.textSpeed);
                    button_Place3.setVisible(true);
                }

            }
            case "Grasslands" -> {
                ii = new ImageIcon(getClass().getResource("/realmofallyria/images/grasslands.png"));

                loadText("Explore", button_Place1, false, true, TextLoadingSpeeds.SLOW.textSpeed);
                button_Place1.setVisible(true);

                if (storylineIndex == 13) {
                    loadText(String.format("<html><p align=\"center\">%s's Lair</p></html>", characterNames.get(7)[2]),
                            button_Place2, false, true, TextLoadingSpeeds.SLOW.textSpeed);
                    button_Place2.setVisible(true);
                }

            }
            case "Fortress" -> {
                ii = new ImageIcon(getClass().getResource("/realmofallyria/images/fortress.png"));

            }
            case "Forest" -> {
                ii = new ImageIcon(getClass().getResource("/realmofallyria/images/forest.png"));

            }
            case "Capital" -> {
                ii = new ImageIcon(getClass().getResource("/realmofallyria/images/capital.png"));

            }
            case "Dungeon" -> {
                ii = new ImageIcon(getClass().getResource("/realmofallyria/images/dungeon.png"));

            }

        }

        Image image = (ii).getImage().getScaledInstance(label_GameBackground.getWidth(),
                label_GameBackground.getHeight(), Image.SCALE_SMOOTH);
        ii = new ImageIcon(image);
        label_WildernessBackground.setIcon(ii);
        label_GameBackground.setIcon(ii);
        currentLocation = locationTravelledTo;

        loadText(quest.isQuestCompleted() ? "Quest(!)" : "Quest",
                button_Quest, false, true, TextLoadingSpeeds.SLOW.textSpeed);

        loadText(String.format("Location: %s", currentLocation),
                label_Location, false, true, TextLoadingSpeeds.SLOW.textSpeed);
        openGameScreen();
        warnPlayerHPMP();

    }

    // determines which buttons to show
    private void button_TravelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_TravelActionPerformed

        if (storylineIndex > 9) {

            panel_Game.setVisible(false);
            showReturn();
            panel_Travel.setVisible(true);

            loadText("Civilization",
                    label_Civilization, false, true, TextLoadingSpeeds.SLOW.textSpeed);
            loadText("Wilderness",
                    label_Wilderness, false, true, TextLoadingSpeeds.SLOW.textSpeed);

            button_Village.setVisible(true);
            loadText("Village",
                    button_Village, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
            button_Grasslands.setVisible(true);
            loadText("Grasslands",
                    button_Grasslands, false, true, TextLoadingSpeeds.NORMAL.textSpeed);

        } else {

            messagePopup("Quest Incomplete");
        }

    }//GEN-LAST:event_button_TravelActionPerformed

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="combat/ battle stuff">
    private void generateBattle(String battleMobName,
            String battleMobAffinity,
            int battleMobLVL,
            Weapon battleMobWeapon,
            Armor battleMobArmor,
            boolean forcedEncounter) {

        enemy = new Hostile(battleMobName, battleMobAffinity, battleMobLVL, battleMobWeapon, battleMobArmor);
        battle = new Battle(player, enemy);

        if (forcedEncounter) {

            startBattle(true);

        }

    }

    private void startBattle(boolean forceTurn) {

        hideScreens();

        panel_Combat.setVisible(true);
        panel_CombatLog.setVisible(true);
        panel_CombatSkills.setVisible(false);

        if (forceTurn) {
            combatTurn();
        }

    }

    private void updateCombatScreen() {

        button_FleeCombat.setText(String.format("Flee (%.0f%%)", battle.escapeChance));

        label_CombatPlayerAffinity.setText(battle.battlePlayer.typeAffinity);
        label_CombatEnemyAffinity.setText(battle.battleHostile.typeAffinity);

        label_CombatPlayer.setText(String.format("<html>%s (LVL %s)</html>", battle.battlePlayer.name,
                battle.battlePlayer.level));
        label_CombatHP.setText(String.format("Health Points (HP): %.2f / %.2f\n", battle.battlePlayer.currentHP,
                battle.battlePlayer.maxHP));
        label_CombatMP.setText(String.format("Magic Points (MP): %.2f / %.2f\n", battle.battlePlayer.currentMP,
                battle.battlePlayer.maxMP));

        label_CombatEnemy.setText(String.format("<html>%s (LVL %s)</html>", battle.battleHostile.name,
                battle.battleHostile.level));
        label_EnemyHP.setText(String.format("Health Points (HP): %.2f / %.2f\n", battle.battleHostile.currentHP,
                battle.battleHostile.maxHP));
        label_EnemyMP.setText(String.format("Magic Points (MP): %.2f / %.2f\n", battle.battleHostile.currentMP,
                battle.battleHostile.maxMP));

    }

    private void combatTurn() {

        button_UseSkill.setVisible(false);
        button_FleeCombat.setVisible(false);

        if (battle.battlePlayer.currentHP > 0 && battle.battleHostile.currentHP > 0) {

            if (battle.turns.peek().equals(battle.battleHostile.name)) {

                label_CombatLog.setText(battle.takeTurn(enemy.basicAttackSkill, player, enemy));

            } else {

                label_CombatLog.setText(String.format("""
                                        <html>
                                        <head><h2 align="center">%s</h2></head>
                                        </html>
                                        """,
                        (battle.battlePlayer.name + "'s turn")));
                button_UseSkill.setVisible(true);
                button_FleeCombat.setVisible(true);

            }

            updateCombatScreen();

        } else {

            label_CombatLog.setText(battle.battleEnd(false));

        }

    }

    private void button_UseBasicAttackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UseBasicAttackActionPerformed

        skillAction("basicAttackSkill");

    }//GEN-LAST:event_button_UseBasicAttackActionPerformed

    private void skillAction(String attackSkillActionType) {

        Skill actionSkill = new Skill("");

        switch (attackSkillActionType) {

            case "basicAttackSkill" -> {
                actionSkill = player.basicAttackSkill;
            }
            case "skill1" -> {
                actionSkill = player.skill1;
            }
            case "skill2" -> {
                actionSkill = player.skill2;
            }
            case "skill3" -> {
                actionSkill = player.skill3;
            }

        }

        if (player.currentMP >= actionSkill.skillBaseCost) {

            label_CombatLog.setText(battle.takeTurn(actionSkill, enemy, player));
            button_UseSkill.setVisible(false);
            button_FleeCombat.setVisible(false);

            button_UseSkill.setText("Skills");
            panel_CombatLog.setVisible(true);
            panel_CombatSkills.setVisible(false);

            updateCombatScreen();

        } else {

            messagePopup("Insufficient MP");

        }

    }

    private void button_FleeCombatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_FleeCombatActionPerformed

        if (battle.escapeChance > 0) {

            fleeAttempt();

        }

    }//GEN-LAST:event_button_FleeCombatActionPerformed

    private void panel_CombatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_CombatMouseClicked

//        System.out.println("TEST: " + storylineIndex);
//        System.out.println("TEST: " + battle.battleEnded);
        if (battle.battleEnded) {

            player.receiveXPCoinsReward(battle.battleXPGain, battle.battleCoinGain);
            quest.updateTask(battle.battleHostile.name);

            if (storylineIndex == 7) {

                // post slime combat sequence
                speakToNPC(2);

            } else {

                if (player.currentHP < 0) {

                    if (dungeon != null) {
                        dungeon.dungeonEncounters.clear();
                    }
                    if (dungeon != null) {
                        dungeon.dungeonEncounters.clear();
                    }
                    invadingBossLair = false;
                    returnHome("Defeated");

                } else {

                    panel_Combat.setVisible(false);

                    if (invadingBossLair && dungeon != null && !dungeon.dungeonEncounters.isEmpty()
                            || !invadingBossLair && dungeon != null && !dungeon.dungeonEncounters.isEmpty()) {

                        String exploreTurnsString = "";

                        if (invadingBossLair && dungeon != null && !dungeon.dungeonEncounters.isEmpty()) {

                            dungeon.obstructed = false;
                            button_DungeonAttack.setVisible(false);
                            button_DungeonFlee.setVisible(false);
                            exploreTurnsString = String.format("<br> (%s / 10 turns taken)", dungeon.exploreTurns);
//                        exploreDungeon(currentLocation, false);

                        } else if (!invadingBossLair && dungeon != null && !dungeon.dungeonEncounters.isEmpty()) {

                            dungeon.obstructed = false;
                            button_DungeonAttack.setVisible(false);
                            button_DungeonFlee.setVisible(false);
                            exploreTurnsString = String.format("<br> (%s / 10 turns taken)", dungeon.exploreTurns);
//                        exploreDungeon(currentLocation, false);
                        }

                        panel_Dungeon.setVisible(true);
                        label_EncounterLog.setText(String.format(""" 
                                            <html>

                                            <head>
                                            <h2 align="center">
                                            %s
                                            </h2>
                                            </head>

                                            <body>
                                            <p align="center">
                                            %s %s
                                            </p>
                                            </body>

                                            </html>
                                            """, String.format("%s (LVL %s) Defeated", battle.battleHostile.name, battle.battleHostile.level),
                                "Battle ended.",
                                exploreTurnsString));

                    } else {

                        openGameScreen();

                        if (quest.isQuestCompleted()) {

                            messagePopup("Boss Defeated");

                            if (invadingBossLair) {

                                nextDialogueArray();
                                panel_Storyline.setVisible(false);

                            }

                        }
                        invadingBossLair = false;

                    }

                }

            }

            warnPlayerHPMP();

            if (player.accumulatedLVL > 0) {

                messagePopup("Level increased!");

            }

            battle = null;

        } else {

            combatTurn();

        }

    }//GEN-LAST:event_panel_CombatMouseClicked

    private void button_UseSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UseSkillActionPerformed

        if (button_UseSkill.getText().equals("Skills")) {

            button_UseSkill.setText("Cancel");
            panel_CombatLog.setVisible(false);
            panel_CombatSkills.setVisible(true);
            // <editor-fold desc="show skill buttons">
            button_UseBasicAttack.setVisible(player.basicAttackSkill != null);
            button_UseBasicAttack.setText(player.basicAttackSkill.skillName);

            button_UseSkill1.setVisible(player.skill1 != null);
            button_UseSkill1.setText(player.skill1 != null
                    ? String.format("""
                                  <html>
                                  
                                  <body>
                                  <p align="center">
                                  %s <br>(costs %s MP)
                                  </p>
                                  </body>
                                  
                                  </html>
                                  """, player.skill1.skillName, player.skill1.skillBaseCost)
                    : "");

            button_UseSkill2.setVisible(player.skill2 != null);
            button_UseSkill2.setText(player.skill2 != null
                    ? String.format("""
                                  <html>
                                  
                                  <body>
                                  <p align="center">
                                  %s <br>(costs %s MP)
                                  </p>
                                  </body>
                                  
                                  </html>
                                  """, player.skill2.skillName, player.skill2.skillBaseCost)
                    : "");

            button_UseSkill3.setVisible(player.skill3 != null);
            button_UseSkill3.setText(player.skill3 != null
                    ? String.format("""
                                  <html>
                                  
                                  <body>
                                  <p align="center">
                                  %s <br>(costs %s MP)
                                  </p>
                                  </body>
                                  
                                  </html>
                                  """, player.skill3.skillName, player.skill3.skillBaseCost)
                    : "");
            // </editor-fold>

        } else {

            button_UseSkill.setText("Skills");
            panel_CombatLog.setVisible(true);
            panel_CombatSkills.setVisible(false);

        }

    }//GEN-LAST:event_button_UseSkillActionPerformed

    private void button_UseSkill2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UseSkill2ActionPerformed

        skillAction("skill2");

    }//GEN-LAST:event_button_UseSkill2ActionPerformed

    private void button_UseSkill3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UseSkill3ActionPerformed

        skillAction("skill3");

    }//GEN-LAST:event_button_UseSkill3ActionPerformed

    private void button_UseSkill1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UseSkill1ActionPerformed

        skillAction("skill1");

    }//GEN-LAST:event_button_UseSkill1ActionPerformed

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="warning/ popup stuff">
    private void messagePopup(String type) {

        String warningLabel = "";

        // determines if the popup is removed from the warningsMap if the close button is pressed.
        // stops repetitive popup
        boolean isCloseDispose = true;

        switch (type) {
            case "Level increased!" -> {
                warningLabel = String.format("""
                                             <p>
                                             %s %s %s %s
                                             </p>
                                             """, String.format("<br>LVL %s >> LVL %s", (player.level - player.accumulatedLVL), player.level),
                        String.format("<br>+%.2f XP gained.", battle.battleXPGain),
                        String.format("<br>You have acquired (+%s) attribute point(s).", player.attributePoints),
                        "<br>(HP and MP restored to full)"
                );
                player.accumulatedLVL = 0;
            }
            case "Full HP and MP" -> {
                warningLabel = "Your HP and MP are both full.";
            }
            case "Low HP and MP" -> {
                warningLabel = "You are on the brink of death and your magic power stores are nearly exhausted. Exploring the dungeon at this state is utterly unwise.";
                isCloseDispose = false;
            }
            case "Low HP" -> {
                warningLabel = "You are on the brink of death. Exploring the dungeon at this state is utterly unwise.";
                isCloseDispose = false;
            }
            case "Low MP" -> {
                warningLabel = "Your magic power stores are nearly exhausted. Exploring the dungeon at this state is utterly unwise.";
                isCloseDispose = false;
            }
            case "Quest Incomplete" -> {
                warningLabel = "You must complete your quest before continuing.";
            }
            case "Quest Completed" -> {
                player.receiveXPCoinsReward(quest.questXPReward, quest.questCoinsReward);
                warningLabel = String.format("""
                                             <p align="center">
                                             %s %s
                                             </p>
                                             """, quest.questName,
                        String.format("<br>Rewards Received: +%.2f XP +%s", quest.questXPReward, convertCoins(quest.questCoinsReward)));
                openGameScreen();
            }
            case "Boss Defeated" -> {
                warningLabel = String.format("""
                                             <p align="center">
                                             %s %s
                                             </p>
                                             """, "<br>Your actions have brought the Realm of Allyria closer to peace.",
                        String.format("<br>You may claim the following rewards: %.2f XP %s", quest.questXPReward, convertCoins(quest.questCoinsReward)));
                openGameScreen();
            }
            case "Insufficient Coins" -> {
                warningLabel = "You do not have enough coins to purchase this item.";
            }
            case "Insufficient MP" -> {
                warningLabel = "You do not have enough MP to use this skill.";
            }
            case "Debug Mode" -> {
                warningLabel = "Debug Mode has been enabled.";
                player.totalCoins += 1000000;
                player.strengthPoints += 200;
                player.agilityPoints += 200;
                player.confirmAttributeChanges();
            }
        }

        warningLabel = String.format("<html> %s </html>", warningLabel);

        // Check if a warning of this type already exists
        if (warningsMap.get(type) != null) {
            warningsMap.get(type).dispose(); // Dispose of the existing one
        }

        // Create a new instance and store it in the map
        WarningPopup newPopup = new WarningPopup(type, warningLabel, isCloseDispose);
        warningsMap.put(type, newPopup);
        newPopup.setVisible(true);

    }

    private void warnPlayerHPMP() {

        boolean lowHP = player.currentHP < player.maxHP * 0.25 && player.currentHP > 0;
        boolean lowMP = player.currentMP < player.maxMP * 0.25 && player.currentMP > 0;

        if (!warningsMap.containsKey("Full HP and MP")) {
            if (lowHP && lowMP) {
                messagePopup("Low HP and MP");
            } else {
                if (lowHP && !warningsMap.containsKey("Low HP")) {
                    messagePopup("Low HP");
                }
                if (lowMP && !warningsMap.containsKey("Low MP")) {
                    messagePopup("Low MP");
                }
            }
        }

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="dungeon stuff">
    private void exploreDungeon(String wildernessLocation, boolean newExploration) {

        hideScreens();

        panel_Dungeon.setVisible(true);

        if (newExploration) {

            label_DungeonLocation.setText(invadingBossLair == true ? button_Place2.getText() : wildernessLocation);
            label_EncounterLog.setText("(CLICK TO START)");

            button_DungeonAttack.setVisible(false);
            button_DungeonFlee.setVisible(false);

            switch (wildernessLocation) {

                case "Grasslands" -> {

                    if (invadingBossLair) {

                        // <editor-fold desc="Grasslands boss lair stuff">
                        String[] grasslandBossLairMonsters = {"Imp", getCharacterFullName(7, true)};
                        String[][] grasslandBossLairAffinities = {
                            {"Madeis", "Virtus"},
                            {"Madeis", "Madeis"}
                        };
                        String[][] grasslandBossLairEquipment = {
                            {"Unarmored", "Body"},
                            {"Unarmored", "Body"}
                        };
                        String[] grasslandBossLairBasicSkills = {
                            "Tackle",
                            "Club"
                        };
                        String[] grasslandBossLairScenicViews = {
                            "There are unorganized crates everywhere.",
                            "The flaps to an empty tent open with the wind.",
                            "A bloodstained rag hangs from a fence post.",
                            "A pile of armor lies in disarray.",
                            "You find a stack to rusted weapons.",
                            "You hear muffled voices up ahead.",
                            "A rusted helmet lies forgotten in the dirt.",
                            "A murder of crows circles overhead.",
                            "A dog growls quietly from a nearby pen.",
                            "You pass a tent bearing strange symbols.",
                            "A menacing banner looms overhead."
                        };
                        // </editor-fold>

                        dungeon = new BossLair(0,
                                grasslandBossLairMonsters,
                                grasslandBossLairAffinities,
                                grasslandBossLairEquipment,
                                grasslandBossLairBasicSkills,
                                grasslandBossLairScenicViews,
                                player.level);

                    } else {

                        // <editor-fold desc="Grasslands wilderness stuff">
                        String[] grasslandMonsters = {"Slime", "Goblin", "Wolf"};
                        String[][] grasslandAffinities = {
                            {"Madeis", "Celeritas"},
                            {"Sanitas", "Celeritas"},
                            {"Virtus", "Sanitas"}
                        };
                        String[][] grasslandEquipment = {
                            {"Unarmored", "Body"},
                            {"Unarmored", "Club"},
                            {"Unarmored", "Mouth"}
                        };
                        String[] grasslandBasicAttacks = {
                            "Tackle",
                            "Club",
                            "Bite"
                        };
                        String[] grasslandScenicViews = {
                            "You come across a pretty flower.",
                            "You come across a lonesome tree.",
                            "The tall grass blades dance along the wind.",
                            "A powerful gust of wind bursts across the plains.",
                            "A flock of wild sheep graze in the open field.",
                            "A swallow drifts across the sky.",
                            "A shallow creek runs along your pathway.",
                            "A lone hawk circles high above.",
                            "A breeze carries the scent of wild herbs.",
                            "The sun peeks through scattered clouds.",
                            "A butterfly flits between wildflowers.",
                            "A trail of ants marches through the dirt.",
                            "A cloud drifts lazily overhead.",
                            "The grass crunches beneath your steps.",
                            "A beetle flips onto its back, struggling.",
                            "A pair of birds chase each other midair.",
                            "Tiny yellow flowers dot the ground.",
                            "You notice paw prints in the dirt.",
                            "A warm breeze tickles your face.",
                            "The sun casts long shadows over the field.",
                            "An old, twisted root snakes along the surface.",
                            "A small hill rises gently ahead.",
                            "Distant mountains loom on the horizon.",
                            "A puddle reflects the passing clouds.",
                            "A snail crawls slowly along a blade of grass.",
                            "A gust of wind sends a swirl of dust skyward.",
                            "You see a worn path cutting through the field.",
                            "A dandelion’s seeds float through the air."
                        };
                        // </editor-fold>

                        dungeon = new Wilderness(0,
                                grasslandMonsters,
                                grasslandAffinities,
                                grasslandEquipment,
                                grasslandBasicAttacks,
                                grasslandScenicViews,
                                player.level);

                    }

                }

            }

        } else {

            if (dungeon.exploreTurns < 20 && !dungeon.obstructed) {

                switch (dungeon.exploreTurn()) {

                    case "Combat" -> {

                        dungeon.generateDungeonMob();

                        generateBattle(dungeon.dungeonMobName,
                                dungeon.dungeonMobAffinity,
                                dungeon.dungeonMobLVL,
                                dungeon.dungeonMobWeapon,
                                dungeon.dungeonMobArmor,
                                false
                        );

                        label_EncounterLog.setText(String.format(""" 
                                            <html>

                                            <head>
                                            <h2 align="center">
                                            %s
                                            </h2>
                                            </head>

                                            <body>
                                            <p align="center">
                                            %s %s
                                            </p>
                                            </body>

                                            </html>
                                            """, String.format("Encountered %s (LVL %s)", battle.battleHostile.name, battle.battleHostile.level),
                                "Flee or attack.",
                                String.format("<br>(%s / %s turns taken)", dungeon.exploreTurns, dungeon.exploreTurns + dungeon.dungeonEncounters.size())));

                        button_DungeonAttack.setVisible(true);
                        button_DungeonFlee.setVisible(true);
                        button_DungeonReturn.setVisible(false);
                        button_DungeonFlee.setText(String.format("Flee (%.0f%%)", battle.escapeChance));

                    }
                    case "Scenery" -> {

                        // makes sure the same scenic view is not used twice in a row
                        int newScenicViewIndex = 0;
                        while (newScenicViewIndex == dungeon.recentScenicViewPromptIndex) {

                            newScenicViewIndex = gameRandomizer.nextInt(dungeon.scenicViewPrompts.length);

                        }
                        dungeon.recentScenicViewPromptIndex = newScenicViewIndex;

                        button_DungeonReturn.setVisible(true);

                        label_EncounterLog.setText(String.format(""" 
                                            <html>

                                            <head>
                                            <h2 align="center">
                                            %s
                                            </h2>
                                            </head>

                                            <body>
                                            <p align="center">
                                            %s %s
                                            </p>
                                            </body>

                                            </html>
                                            """, dungeon.scenicViewPrompts[newScenicViewIndex],
                                "(CLICK TO CONTINUE)",
                                String.format("<br> (%s / 10 turns taken)", dungeon.exploreTurns)));
                    }
                    case "Boss" -> {

                        dungeon.generateBossMob();

                        generateBattle(dungeon.dungeonMobName,
                                dungeon.dungeonMobAffinity,
                                dungeon.dungeonMobLVL,
                                dungeon.dungeonMobWeapon,
                                dungeon.dungeonMobArmor,
                                false);
                        battle.escapeChance = 0;

                        label_EncounterLog.setText(String.format(""" 
                                            <html>

                                            <head>
                                            <h2 align="center">
                                            %s
                                            </h2>
                                            </head>

                                            <body>
                                            <p align="center">
                                            %s %s
                                            </p>
                                            </body>

                                            </html>
                                            """, String.format("Encountered %s (LVL %s)", battle.battleHostile.name, battle.battleHostile.level),
                                "You have encountered the lair's boss.",
                                "<br>(CLICK TO CONTINUE)",
                                String.format("<br>(%s / %s turns taken)", dungeon.exploreTurns, dungeon.exploreTurns + dungeon.dungeonEncounters.size())));

                        button_DungeonAttack.setVisible(false);
                        button_DungeonFlee.setVisible(false);
                        button_DungeonReturn.setVisible(false);

                    }

                }

            } else if (invadingBossLair && dungeon.exploreTurns >= 10) {

                speakToNPC(7);

            } else if (!invadingBossLair && dungeon.exploreTurns >= 20 && !dungeon.obstructed) {

                travelToLocation(currentLocation);

            }

        }

    }

    private void panel_DungeonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_DungeonMouseClicked

        exploreDungeon(currentLocation, false);

    }//GEN-LAST:event_panel_DungeonMouseClicked

    private void button_DungeonAttackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DungeonAttackActionPerformed

        startBattle(true);

    }//GEN-LAST:event_button_DungeonAttackActionPerformed

    private void button_DungeonFleeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DungeonFleeActionPerformed

        fleeAttempt();

    }//GEN-LAST:event_button_DungeonFleeActionPerformed

    private void button_DungeonReturnActionPerformed(java.awt.event.ActionEvent evt) {

        travelToLocation(currentLocation);
        warnPlayerHPMP();

    }

    private void fleeAttempt() {

//        panel_Combat.setVisible(false);
        boolean fleeFailed = battle.attemptFlee();

        if (panel_Dungeon.isVisible()) {

            if (fleeFailed) {

                startBattle(false);
                updateCombatScreen();
                label_CombatLog.setText(String.format("""
                                        <html>
                                        <head><h2 align="center">%s %s</h2></head>
                                        </html>
                                        """,
                        "Escape unsuccessful",
                        "<br>(CLICK TO CONTINUE)"));

            } else {

                panel_Combat.setVisible(false);
                battle.battleEnd(false);
                handleSuccessfulEscape();

            }

        } else {

            if (fleeFailed) {

                button_FleeCombat.setVisible(false);
                button_UseSkill.setVisible(false);

                battle.moveQueue();
                label_CombatLog.setText(String.format("""
            <html>
            <head><h3 align="center">%s</h3></head>
            <body><p align="center">%s %s</p></body>
            </html>
            """, String.format("%s tried to escape", battle.battlePlayer.name),
                        "Escape unsuccessful",
                        "<br>(CLICK TO CONTINUE)"));

            } else {

                handleSuccessfulEscape();

            }

        }

    }

    private void handleSuccessfulEscape() {

        if (dungeon != null) {
            dungeon.obstructed = false;
        }

        if (dungeon != null) {
            dungeon.obstructed = false;
        }

        panel_Combat.setVisible(false);
        panel_Dungeon.setVisible(true);

        button_DungeonAttack.setVisible(false);
        button_DungeonFlee.setVisible(false);

        label_EncounterLog.setText(String.format("""
        <html>
        <head><h2 align="center">%s</h2></head>
        <body><p align="center">%s %s</p></body>
        </html>
        """, "Escape Successful",
                "(CLICK TO CONTINUE)",
                String.format("<br>(%s / %s turns taken)", dungeon.exploreTurns, dungeon.dungeonEncounters.size())
        ));

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="quest stuff">
    private void button_QuestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_QuestActionPerformed

        panel_Game.setVisible(false);
        showReturn();
        panel_Quest.setVisible(true);

        // used to generate the task string
        String tasksString = "";

        for (String questTaskUpdateKey : quest.QuestTasks.keySet()) {

            if (!questTaskUpdateKey.equals("Completed")) {

                tasksString += String.format("%s (%s/ %s)",
                        questTaskUpdateKey,
                        quest.QuestTasks.get(questTaskUpdateKey)[0],
                        quest.QuestTasks.get(questTaskUpdateKey)[1]);

            }

        }

        loadText(quest.questName, label_QuestTitle, false, true, TextLoadingSpeeds.SLOW.textSpeed);
        loadText(tasksString, label_QuestBody, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText((String.format("Rewards: %.2f XP, %s", quest.questXPReward, convertCoins(quest.questCoinsReward))),
                label_QuestReward, false, true, TextLoadingSpeeds.NORMAL.textSpeed);
        loadText((quest.isQuestCompleted() == true ? "(Completed)" : "(Incomplete)"),
                label_QuestCompleted, false, true, TextLoadingSpeeds.SLOW.textSpeed);

    }//GEN-LAST:event_button_QuestActionPerformed
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="location buttons">
    private void button_VillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_VillageActionPerformed

        travelToLocation("Village");

    }//GEN-LAST:event_button_VillageActionPerformed

    private void button_GrasslandsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_GrasslandsActionPerformed

        travelToLocation("Grasslands");

    }//GEN-LAST:event_button_GrasslandsActionPerformed

    private void button_TownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_TownActionPerformed

        travelToLocation("Town");

    }//GEN-LAST:event_button_TownActionPerformed

    private void button_ForestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ForestActionPerformed

        travelToLocation("Forest");


    }//GEN-LAST:event_button_ForestActionPerformed

    private void button_CityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CityActionPerformed

        travelToLocation("City");


    }//GEN-LAST:event_button_CityActionPerformed

    private void button_DungeonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DungeonActionPerformed

        travelToLocation("Dungeon");


    }//GEN-LAST:event_button_DungeonActionPerformed

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="store stuff buttons">
    private void createStore(String storeLocationName, HashMap<String, Equipment> merchandiseMap, int storeLVL) {

        store.generateMerchandiseInfo(storeLocationName, merchandiseMap, storeLVL);

    }

    private void enterStore(String storeEntered) {

        hideScreens();

        showReturn();
        panel_Shop.setVisible(true);
        label_ShopLocation.setText(storeEntered);

        label_ShopCurrency.setText(String.format("""
                                                 <html>
                                                 <p>
                                                 Coins:%s
                                                 </p>
                                                 </html>
                                                 """, convertCoins(player.totalCoins)));

        currentStore = storeEntered;

//        System.out.println(store.storeMerchandise.get(storeEntered));
//        System.out.println(store.merchandiseInfo.get(storeEntered));
        String[] storeMerchandiseTypes = {"Sword", "Wand", "Bow", "Armor"};

        for (String storeMerchandiseStr : storeMerchandiseTypes) {

            setBuyWeaponText(storeMerchandiseStr);
            setEquipWeaponText(storeMerchandiseStr);

        }

        button_EquipSword.setVisible(!(button_EquipSword.getText().isEmpty()));
        button_EquipBow.setVisible(!(button_EquipBow.getText().isEmpty()));
        button_EquipWand.setVisible(!(button_EquipWand.getText().isEmpty()));
        button_EquipArmor.setVisible(!(button_EquipArmor.getText().isEmpty()));
    }

    private void setBuyWeaponText(String merchandiseButtonType) {

        String buyButtonText;

        if (store.storeMerchandise.get(currentStore).get(merchandiseButtonType).equipmentLVL < 7) {

            buyButtonText = String.format("""
                <html>
                <body>
                <p align="center">
                %s (LVL %s) %s
                <br> cost: %s
                </p>
                </body>
                </html>
                """,
                    store.storeMerchandise.get(currentStore).get(merchandiseButtonType).equipmentName,
                    store.storeMerchandise.get(currentStore).get(merchandiseButtonType).equipmentLVL,
                    String.format("<br>(%s %s %s %s %s)",
                            store.storeMerchandise.get(currentStore).get(merchandiseButtonType).wpStrengthPoints > 0
                            ? String.format("+%s SP", store.storeMerchandise.get(currentStore).get(merchandiseButtonType).wpStrengthPoints)
                            : "",
                            store.storeMerchandise.get(currentStore).get(merchandiseButtonType).wpIntelligencePoints > 0
                            ? String.format("+%s IP", store.storeMerchandise.get(currentStore).get(merchandiseButtonType).wpIntelligencePoints)
                            : "",
                            store.storeMerchandise.get(currentStore).get(merchandiseButtonType).wpAgilityPoints > 0
                            ? String.format("+%s AP", store.storeMerchandise.get(currentStore).get(merchandiseButtonType).wpAgilityPoints)
                            : "",
                            store.storeMerchandise.get(currentStore).get(merchandiseButtonType).arHealthPoints > 0
                            ? String.format("+%s HP", store.storeMerchandise.get(currentStore).get(merchandiseButtonType).arHealthPoints)
                            : "",
                            store.storeMerchandise.get(currentStore).get(merchandiseButtonType).arDefensePoints > 0
                            ? String.format("+%s DP", store.storeMerchandise.get(currentStore).get(merchandiseButtonType).arDefensePoints)
                            : ""),
                    convertCoins(store.storeMerchandise.get(currentStore).get(merchandiseButtonType).coinsWorth
                    )
            );

        } else {

            buyButtonText = String.format("""
                <html>
                <body>
                <p align="center">
                %s
                </p>
                </body>
                </html>
                """, "(MAX LEVEL REACHED)");

        }

        switch (merchandiseButtonType) {

            case "Sword" -> {
                button_BuySword.setText(buyButtonText);
            }
            case "Bow" -> {
                button_BuyBow.setText(buyButtonText);
            }
            case "Wand" -> {
                button_BuyWand.setText(buyButtonText);
            }
            case "Armor" -> {
                button_BuyArmor.setText(buyButtonText);
            }

        }

    }

    private void setEquipWeaponText(String merchandiseButtonType) {

        String equipButtonText = "";

        if (store.boughtMerchandise.get(currentStore) != null
                && store.boughtMerchandise.get(currentStore).get(merchandiseButtonType) != null) {

            if (player.equippedWeapon.equipmentName.equals(store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).equipmentName)
                    || player.equippedArmor.equipmentName.equals(store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).equipmentName)) {

                equipButtonText = String.format("""
                <html>
                <body>
                <p align="center">
                %s (LVL %s) %s %s
                </p>
                </body>
                </html>
                """,
                        store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).equipmentName,
                        store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).equipmentLVL,
                        String.format("<br>(%s %s %s %s %s)",
                                store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).wpStrengthPoints > 0
                                ? String.format("+%s SP", store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).wpStrengthPoints)
                                : "",
                                store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).wpIntelligencePoints > 0
                                ? String.format("+%s IP", store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).wpIntelligencePoints)
                                : "",
                                store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).wpAgilityPoints > 0
                                ? String.format("+%s AP", store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).wpAgilityPoints)
                                : "",
                                store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).arHealthPoints > 0
                                ? String.format("+%s HP", store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).arHealthPoints)
                                : "",
                                store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).arDefensePoints > 0
                                ? String.format("+%s DP", store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).arDefensePoints)
                                : ""),
                        "<br>(CURRENTLY EQUIPPED)"
                );

            } else {

                equipButtonText = String.format("""
                <html>
                <body>
                <p align="center">
                Equip: %s (LVL %s) %s
                </p>
                </body>
                </html>
                """,
                        store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).equipmentName,
                        store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).equipmentLVL,
                        String.format("<br>(%s %s %s %s %s)",
                                store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).wpStrengthPoints > 0
                                ? String.format("+%s SP", store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).wpStrengthPoints)
                                : "",
                                store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).wpIntelligencePoints > 0
                                ? String.format("+%s IP", store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).wpIntelligencePoints)
                                : "",
                                store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).wpAgilityPoints > 0
                                ? String.format("+%s AP", store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).wpAgilityPoints)
                                : "",
                                store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).arHealthPoints > 0
                                ? String.format("+%s HP", store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).arHealthPoints)
                                : "",
                                store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).arDefensePoints > 0
                                ? String.format("+%s DP", store.boughtMerchandise.get(currentStore).get(merchandiseButtonType).arDefensePoints)
                                : "")
                );

            }

            switch (merchandiseButtonType) {

                case "Sword" -> {
                    button_EquipSword.setText("");
                }
                case "Bow" -> {
                    button_EquipBow.setText("");
                }
                case "Wand" -> {
                    button_EquipWand.setText("");
                }
                case "Armor" -> {
                    button_EquipArmor.setText("");
                }

            }

        }

        switch (merchandiseButtonType) {

            case "Sword" -> {
                button_EquipSword.setText(button_EquipSword.getText().isEmpty()
                        ? equipButtonText : button_EquipSword.getText());
            }
            case "Bow" -> {
                button_EquipBow.setText(button_EquipBow.getText().isEmpty()
                        ? equipButtonText : button_EquipBow.getText());
            }
            case "Wand" -> {
                button_EquipWand.setText(button_EquipWand.getText().isEmpty()
                        ? equipButtonText : button_EquipWand.getText());
            }
            case "Armor" -> {
                button_EquipArmor.setText(button_EquipArmor.getText().isEmpty()
                        ? equipButtonText : button_EquipArmor.getText());
            }

        }

    }

    // <editor-fold desc="buy buttons">
    private void button_BuySwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_BuySwordActionPerformed

        buyMerchandiseAction("Sword");

    }//GEN-LAST:event_button_BuySwordActionPerformed

    private void button_BuyWandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_BuyWandActionPerformed

        buyMerchandiseAction("Wand");

    }//GEN-LAST:event_button_BuyWandActionPerformed

    private void button_BuyBowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_BuyBowActionPerformed

        buyMerchandiseAction("Bow");

    }//GEN-LAST:event_button_BuyBowActionPerformed

    private void button_BuyArmorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_BuyArmorActionPerformed

        buyMerchandiseAction("Armor");

    }//GEN-LAST:event_button_BuyArmorActionPerformed

    private void buyMerchandiseAction(String equipmentType) {

        if (store.storeMerchandise.get(currentStore).get(equipmentType).equipmentLVL < 7) {

            if (player.totalCoins > store.storeMerchandise.get(currentStore).get(equipmentType).coinsWorth) {

                player.takeCoins(store.purchaseMerchandise(currentStore, equipmentType));
                equipAction(equipmentType.equals("Armor") ? "" : equipmentType,
                        equipmentType.equals("Armor") ? equipmentType : "");

            } else {

                messagePopup("Insufficient Coins");

            }

        }

    }

    // </editor-fold>
    // <editor-fold desc="equip buttons">
    private void button_EquipSwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_EquipSwordActionPerformed

        equipAction("Sword", "");

    }//GEN-LAST:event_button_EquipSwordActionPerformed

    private void button_EquipWandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_EquipWandActionPerformed

        equipAction("Wand", "");


    }//GEN-LAST:event_button_EquipWandActionPerformed

    private void button_EquipBowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_EquipBowActionPerformed

        equipAction("Bow", "");

    }//GEN-LAST:event_button_EquipBowActionPerformed

    private void button_EquipArmorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_EquipArmorActionPerformed

        equipAction("", "Armor");

    }//GEN-LAST:event_button_EquipArmorActionPerformed

    private void equipAction(String equipWeapon, String equipArmor) {

        player.equipGear(equipWeapon.isEmpty()
                ? player.equippedWeapon : store.equipBoughtMerchandise(currentStore, equipWeapon),
                equipArmor.isEmpty()
                ? player.equippedArmor : store.equipBoughtMerchandise(currentStore, equipArmor));
        enterStore(currentStore);

    }

    // </editor-fold>    
    // -----------------------------------------------------------------------------------------------------------
    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="choose skill stuff">
    private void button_ChooseSkill1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ChooseSkill1ActionPerformed

        chooseSkill(0);

    }//GEN-LAST:event_button_ChooseSkill1ActionPerformed

    private void button_ChooseSkill3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ChooseSkill3ActionPerformed

        chooseSkill(2);

    }//GEN-LAST:event_button_ChooseSkill3ActionPerformed

    private void button_ChooseSkill2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ChooseSkill2ActionPerformed

        chooseSkill(1);

    }//GEN-LAST:event_button_ChooseSkill2ActionPerformed

    private void chooseSkill(int skillIndex) {

        player.equipSkill(availableSkills[skillIndex], availableSkills[skillIndex].skillCostModifier, true);

        if (storylineIndex > 6) {
            travelToLocation(currentLocation);
        } else {
            nextDialogueArray();
        }

        panel_ChooseSkill.setVisible(false);

    }

    private void giveSkill(int skillsLevel) {

        panel_Storyline.setVisible(false);
        panel_ChooseSkill.setVisible(true);

        String skill1Name = "";
        String skill1Description = "";
        String skill2Name = "";
        String skill2Description = "";
        String skill3Name = "";
        String skill3Description = "";

        switch (skillsLevel) {

            case 0 -> {
                skill1Name = "Heal";
                skill1Description = String.format("""
                                                 <html>
                                                 <p>
                                                 %s
                                                 %s
                                                 </p>
                                                 </html>
                                                 """,
                        "Restores your HP by 25% of maximum HP",
                        String.format("<br>costs %s MP (and cost increases by 1 per IP if your IP goes above 8.)",
                                (player.intelligencePoints > 8
                                        ? 20 * (player.intelligencePoints) : 20)));
                availableSkills[0] = new Skill(skill1Name);

                skill2Name = "Fireball";
                skill2Description = String.format("""
                                                 <html>
                                                 <p>
                                                 %s
                                                 %s
                                                 </p>
                                                 </html>
                                                 """,
                        "Deals 2.5x magical damage and has a 25% chance to burn your enemy for three turns (burns their health by 5-10%).",
                        String.format("<br>costs %s MP (and cost increases by 1 per IP if your IP goes above 8.)",
                                (player.intelligencePoints > 8
                                        ? 25 * (player.intelligencePoints) : 25)));
                availableSkills[1] = new Skill(skill2Name);

                skill3Name = "True Strike";
                skill3Description = String.format("""
                                                 <html>
                                                 <p>
                                                 %s
                                                 %s
                                                 </p>
                                                 </html>
                                                 """,
                        "Deals 1.5x physical damage and ignores the enemy's physical defense.",
                        String.format("<br>costs %s MP (and cost increases by 2 per IP if your IP goes above 8.)",
                                (player.intelligencePoints > 8
                                        ? 30 * (player.intelligencePoints) : 30)));
                availableSkills[2] = new Skill(skill3Name);

            }

        }

        loadText(skill1Name,
                button_ChooseSkill1, false, true, TextLoadingSpeeds.SLOW.textSpeed);
        loadText(skill2Name,
                button_ChooseSkill2, false, true, TextLoadingSpeeds.SLOW.textSpeed);
        loadText(skill3Name,
                button_ChooseSkill3, false, true, TextLoadingSpeeds.SLOW.textSpeed);

        label_ChooseSkill1.setText(skill1Description);
        label_ChooseSkill2.setText(skill2Description);
        label_ChooseSkill3.setText(skill3Description);

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------
    // <editor-fold desc="skill panel stuff">
    private void openSkills() {

        panel_Inventory.setVisible(false);
        panel_Skills.setVisible(true);
        loadSkillButton();

        loadSkillText(player.basicAttackSkill,
                label_BasicAttackName,
                label_BasicAttackDmg,
                label_BasicAttackCost,
                label_BasicAttackInflict);
        loadSkillText(player.skill1,
                label_Skill1Name,
                label_Skill1Dmg,
                label_Skill1Cost,
                label_Skill1Inflict);
        loadSkillText(player.skill2,
                label_Skill2Name,
                label_Skill2Dmg,
                label_Skill2Cost,
                label_Skill2Inflict);
        loadSkillText(player.skill3,
                label_Skill3Name,
                label_Skill3Dmg,
                label_Skill3Cost,
                label_Skill3Inflict);

    }

    private void loadSkillText(Skill givenSkill, JLabel skillName, JLabel skillDmg, JLabel skillCost, JLabel skillInflict) {

        String skillNameStr = "(NO SKILL EQUIPPED)";

        if (givenSkill != null) {

            skillNameStr = givenSkill.skillName;
            loadText(givenSkill.effects.containsKey("PhysicalDamageMultiplier")
                    ? String.format("Physical Damage: %s", givenSkill.effects.get("PhysicalDamageMultiplier"))
                    : givenSkill.effects.containsKey("MagicalDamageMultiplier")
                    ? String.format("Magical Damage: %s", givenSkill.effects.get("MagicalDamageMultiplier"))
                    : "(No Damage)",
                    skillDmg, false, true, TextLoadingSpeeds.SLOW.textSpeed);
            loadText(givenSkill.skillBaseCost > 0
                    ? String.format("cost %s MP (cost increase %s MP)", givenSkill.skillBaseCost, givenSkill.skillCostIncrease) : "No MP Cost",
                    skillCost, false, true, TextLoadingSpeeds.SLOW.textSpeed);
            loadText(givenSkill.effects.containsKey("SelfInflicted") ? "(Self-inflicted)" : "(Attack)",
                    skillInflict, false, true, TextLoadingSpeeds.SLOW.textSpeed);

        }

        loadText(skillNameStr,
                skillName, false, true, TextLoadingSpeeds.SLOW.textSpeed);

    }

    private void loadSkillButton() {

        String buttonText = "Skills";

        if (panel_Inventory.isVisible()) {

            panel_Inventory.setComponentZOrder(button_Skills, panel_Inventory.getComponentCount() - 1);

        } else {

            panel_Skills.setComponentZOrder(button_Skills, panel_Inventory.getComponentCount() - 1);
            buttonText = "Inventory";

        }

        loadText(buttonText,
                button_Skills, false, true, TextLoadingSpeeds.NORMAL.textSpeed);

    }

    private void button_SkillsActionPerformed(java.awt.event.ActionEvent evt) {

        if (panel_Inventory.isVisible()) {

            openSkills();

        } else {

            openInventory();

        }

    }

    // </editor-fold>
    // -----------------------------------------------------------------------------------------------------------
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new Game().setVisible(true);
        });
    }

    // <editor-fold desc="java swing variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_APAddition;
    private javax.swing.JButton button_AttributesConfirm;
    private javax.swing.JButton button_AttributesReset;
    private javax.swing.JButton button_BuyArmor;
    private javax.swing.JButton button_BuyBow;
    private javax.swing.JButton button_BuySword;
    private javax.swing.JButton button_BuyWand;
    private javax.swing.JButton button_Celeritas;
    private javax.swing.JButton button_ChooseSkill1;
    private javax.swing.JButton button_ChooseSkill2;
    private javax.swing.JButton button_ChooseSkill3;
    private javax.swing.JButton button_City;
    private javax.swing.JButton button_Confirm;
    private javax.swing.JButton button_CrudeWand;
    private javax.swing.JButton button_DPAddition;
    private javax.swing.JButton button_Dungeon;
    private javax.swing.JButton button_DungeonAttack;
    private javax.swing.JButton button_DungeonFlee;
    private javax.swing.JButton button_DungeonReturn;
    private javax.swing.JButton button_EquipArmor;
    private javax.swing.JButton button_EquipBow;
    private javax.swing.JButton button_EquipSword;
    private javax.swing.JButton button_EquipWand;
    private javax.swing.JButton button_FleeCombat;
    private javax.swing.JButton button_Forest;
    private javax.swing.JButton button_Grasslands;
    private javax.swing.JButton button_HPAddition;
    private javax.swing.JButton button_IPAddition;
    private javax.swing.JButton button_Inventory;
    private javax.swing.JButton button_IronSword;
    private javax.swing.JButton button_Madeis;
    private javax.swing.JButton button_Place1;
    private javax.swing.JButton button_Place2;
    private javax.swing.JButton button_Place3;
    private javax.swing.JButton button_Prince;
    private javax.swing.JButton button_Princess;
    private javax.swing.JButton button_Quest;
    private javax.swing.JButton button_Return;
    private javax.swing.JButton button_SPAddition;
    private javax.swing.JButton button_Sanitas;
    private javax.swing.JButton button_SimpleBow;
    private javax.swing.JButton button_Skills;
    private javax.swing.JButton button_Status;
    private javax.swing.JButton button_Town;
    private javax.swing.JButton button_Travel;
    private javax.swing.JButton button_Tutela;
    private javax.swing.JButton button_UseBasicAttack;
    private javax.swing.JButton button_UseSkill;
    private javax.swing.JButton button_UseSkill1;
    private javax.swing.JButton button_UseSkill2;
    private javax.swing.JButton button_UseSkill3;
    private javax.swing.JButton button_Village;
    private javax.swing.JButton button_Virtus;
    private javax.swing.JLabel label_APAddition;
    private javax.swing.JLabel label_AgilityPoints;
    private javax.swing.JLabel label_Armor;
    private javax.swing.JLabel label_AvailablePoints;
    private javax.swing.JLabel label_BasicAttackCost;
    private javax.swing.JLabel label_BasicAttackDescription;
    private javax.swing.JLabel label_BasicAttackDmg;
    private javax.swing.JLabel label_BasicAttackInflict;
    private javax.swing.JLabel label_BasicAttackLevel;
    private javax.swing.JLabel label_BasicAttackName;
    private javax.swing.JLabel label_CC;
    private javax.swing.JLabel label_Celeritas;
    private javax.swing.JLabel label_ChooseSkill1;
    private javax.swing.JLabel label_ChooseSkill2;
    private javax.swing.JLabel label_ChooseSkill3;
    private javax.swing.JLabel label_Civilization;
    private javax.swing.JLabel label_CombatEnemy;
    private javax.swing.JLabel label_CombatEnemyAffinity;
    private javax.swing.JLabel label_CombatHP;
    private javax.swing.JLabel label_CombatLog;
    private javax.swing.JLabel label_CombatMP;
    private javax.swing.JLabel label_CombatPlayer;
    private javax.swing.JLabel label_CombatPlayerAffinity;
    private javax.swing.JLabel label_CrudeWand;
    private javax.swing.JLabel label_DPAddition;
    private javax.swing.JLabel label_Dash1;
    private javax.swing.JLabel label_Dash10;
    private javax.swing.JLabel label_Dash11;
    private javax.swing.JLabel label_Dash12;
    private javax.swing.JLabel label_Dash13;
    private javax.swing.JLabel label_Dash14;
    private javax.swing.JLabel label_Dash15;
    private javax.swing.JLabel label_Dash2;
    private javax.swing.JLabel label_Dash3;
    private javax.swing.JLabel label_Dash4;
    private javax.swing.JLabel label_Dash5;
    private javax.swing.JLabel label_Dash6;
    private javax.swing.JLabel label_Dash7;
    private javax.swing.JLabel label_Dash8;
    private javax.swing.JLabel label_Dash9;
    private javax.swing.JLabel label_DefensePoints;
    private javax.swing.JLabel label_DungeonLocation;
    private javax.swing.JLabel label_EncounterLog;
    private javax.swing.JLabel label_EnemyHP;
    private javax.swing.JLabel label_EnemyMP;
    private javax.swing.JLabel label_GameBackground;
    private javax.swing.JLabel label_GameCurrency;
    private javax.swing.JLabel label_GameHP;
    private javax.swing.JLabel label_GameMP;
    private javax.swing.JLabel label_GameXP;
    private javax.swing.JLabel label_GearAP;
    private javax.swing.JLabel label_GearCC;
    private javax.swing.JLabel label_GearDP;
    private javax.swing.JLabel label_GearHP;
    private javax.swing.JLabel label_GearIP;
    private javax.swing.JLabel label_GearMDef;
    private javax.swing.JLabel label_GearMDmg;
    private javax.swing.JLabel label_GearPDef;
    private javax.swing.JLabel label_GearPDmg;
    private javax.swing.JLabel label_GearSP;
    private javax.swing.JLabel label_HPAddition;
    private javax.swing.JLabel label_Header;
    private javax.swing.JLabel label_HealthPoints;
    private javax.swing.JLabel label_HomeLabel;
    private javax.swing.JLabel label_HomeReturn;
    private javax.swing.JLabel label_IPAddition;
    private javax.swing.JLabel label_IntelligencePoints;
    private javax.swing.JLabel label_IntroBackground;
    private javax.swing.JLabel label_IntroBody;
    private javax.swing.JLabel label_IntroHeader;
    private javax.swing.JLabel label_IronSword;
    private javax.swing.JLabel label_Level;
    private javax.swing.JLabel label_Location;
    private javax.swing.JLabel label_MDef;
    private javax.swing.JLabel label_MDmg;
    private javax.swing.JLabel label_Madeis;
    private javax.swing.JLabel label_PDef;
    private javax.swing.JLabel label_PDmg;
    private javax.swing.JLabel label_PlayerAffinity;
    private javax.swing.JLabel label_PlayerName;
    private javax.swing.JLabel label_QuestBody;
    private javax.swing.JLabel label_QuestCompleted;
    private javax.swing.JLabel label_QuestReward;
    private javax.swing.JLabel label_QuestTitle;
    private javax.swing.JLabel label_SPAddition;
    private javax.swing.JLabel label_Sanitas;
    private javax.swing.JLabel label_ShopCurrency;
    private javax.swing.JLabel label_ShopLocation;
    private javax.swing.JLabel label_SimpleBow;
    private javax.swing.JLabel label_Skill1Cost;
    private javax.swing.JLabel label_Skill1Description;
    private javax.swing.JLabel label_Skill1Dmg;
    private javax.swing.JLabel label_Skill1Inflict;
    private javax.swing.JLabel label_Skill1Level;
    private javax.swing.JLabel label_Skill1Name;
    private javax.swing.JLabel label_Skill2Cost;
    private javax.swing.JLabel label_Skill2Description;
    private javax.swing.JLabel label_Skill2Dmg;
    private javax.swing.JLabel label_Skill2Inflict;
    private javax.swing.JLabel label_Skill2Level;
    private javax.swing.JLabel label_Skill2Name;
    private javax.swing.JLabel label_Skill3Cost;
    private javax.swing.JLabel label_Skill3Description;
    private javax.swing.JLabel label_Skill3Dmg;
    private javax.swing.JLabel label_Skill3Inflict;
    private javax.swing.JLabel label_Skill3Level;
    private javax.swing.JLabel label_Skill3Name;
    private javax.swing.JLabel label_StorylineText;
    private javax.swing.JLabel label_StrengthPoints;
    private javax.swing.JLabel label_Talker;
    private javax.swing.JLabel label_Title;
    private javax.swing.JLabel label_TotalAP;
    private javax.swing.JLabel label_TotalCC;
    private javax.swing.JLabel label_TotalDP;
    private javax.swing.JLabel label_TotalHP;
    private javax.swing.JLabel label_TotalIP;
    private javax.swing.JLabel label_TotalMDef;
    private javax.swing.JLabel label_TotalMDmg;
    private javax.swing.JLabel label_TotalPDef;
    private javax.swing.JLabel label_TotalPDmg;
    private javax.swing.JLabel label_TotalSP;
    private javax.swing.JLabel label_Tutela;
    private javax.swing.JLabel label_Virtus;
    private javax.swing.JLabel label_Weapon;
    private javax.swing.JLabel label_Wilderness;
    private javax.swing.JLabel label_WildernessBackground;
    private javax.swing.JPanel panel_AffinitiesMenu;
    private javax.swing.JPanel panel_AttributesActions;
    private javax.swing.JPanel panel_AttributesAddition;
    private javax.swing.JPanel panel_AttributesAdditionButtons;
    private javax.swing.JPanel panel_ChooseSkill;
    private javax.swing.JPanel panel_Combat;
    private javax.swing.JPanel panel_CombatLog;
    private javax.swing.JPanel panel_CombatSkills;
    private javax.swing.JPanel panel_Dashes;
    private javax.swing.JPanel panel_Dashes1;
    private javax.swing.JPanel panel_Dashes2;
    private javax.swing.JPanel panel_Dungeon;
    private javax.swing.JPanel panel_Game;
    private javax.swing.JPanel panel_GearAddition;
    private javax.swing.JPanel panel_Home;
    private javax.swing.JPanel panel_Intro;
    private javax.swing.JPanel panel_Inventory;
    private javax.swing.JPanel panel_LocationPanel;
    private javax.swing.JPanel panel_Main;
    private javax.swing.JPanel panel_PickRescue;
    private javax.swing.JPanel panel_PlayerStats;
    private javax.swing.JPanel panel_Quest;
    private javax.swing.JPanel panel_Shop;
    private javax.swing.JPanel panel_Skills;
    private javax.swing.JPanel panel_StartingGear;
    private javax.swing.JPanel panel_Status;
    private javax.swing.JPanel panel_Storyline;
    private javax.swing.JPanel panel_SubGearAddition;
    private javax.swing.JPanel panel_SubTotal;
    private javax.swing.JPanel panel_Total;
    private javax.swing.JPanel panel_Travel;
    private javax.swing.JTextField textField_NameField;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

}
