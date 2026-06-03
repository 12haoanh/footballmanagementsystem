package footballmanagementsystem;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Collections;
import java.util.Comparator;

public class FootballManagementSystem {

    // =========================================================================
    // [FILE 1] cohuongdoituong.java
    // =========================================================================
    public static class Source_cohuongdoituong {
        
        // Class Player
        public static class Player {

            private final String playerId;
            private final String fullName;
            private final int age;
            private final String nationality;
            private String position;

            public Player(String playerId,
                          String fullName,
                          int age,
                          String nationality,
                          String position) {

                this.playerId = playerId;
                this.fullName = fullName;
                this.age = age;
                this.nationality = nationality;
                this.position = position;
            }

            public String getPlayerId() {
                return playerId;
            }

            public String getFullName() {
                return fullName;
            }

            public int getAge() {
                return age;
            }

            public String getNationality() {
                return nationality;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }
        }

        // Class PlayerManager
        public static class PlayerManager {

            private final ArrayList<Player> list = new ArrayList<>();

            public void addPlayer(Player p) {

                for (Player player : list) {

                    if (player.getPlayerId()
                            .equalsIgnoreCase(p.getPlayerId())) {

                        System.out.println("Duplicated ID!");
                        return;
                    }
                }

                list.add(p);

                System.out.println("Player Added Successfully!");
            }

            public void updatePlayer(String id,
                                     String newPosition) {

                for (Player p : list) {

                    if (p.getPlayerId()
                            .equalsIgnoreCase(id)) {

                        p.setPosition(newPosition);

                        System.out.println("Updated Successfully!");
                        return;
                    }
                }

                System.out.println("Player Not Found!");
            }

            public void displayPlayers() {

                if (list.isEmpty()) {

                    System.out.println("No Players!");
                    return;
                }

                System.out.println("\n===== PLAYER LIST =====");

                for (Player p : list) {

                    System.out.println(
                            p.getPlayerId() + " | " +
                            p.getFullName() + " | " +
                            p.getAge() + " | " +
                            p.getNationality() + " | " +
                            p.getPosition()
                    );
                }
            }

            public void searchPlayer(String keyword) {

                boolean found = false;

                for (Player p : list) {

                    if (p.getFullName()
                            .toLowerCase()
                            .contains(keyword.toLowerCase())) {

                        System.out.println(
                                p.getPlayerId() + " | " +
                                p.getFullName() + " | " +
                                p.getPosition()
                        );

                        found = true;
                    }
                }

                if (!found) {

                    System.out.println("Player Not Found!");
                }
            }
        }

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);

            PlayerManager manager = new PlayerManager();

            while (true) {

                System.out.println("\n===== MENU =====");
                System.out.println("1. Add Player");
                System.out.println("2. Update Player");
                System.out.println("3. View Players");
                System.out.println("4. Search Player");
                System.out.println("5. Exit");

                System.out.print("Choose: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:

                        System.out.print("Player ID: ");
                        String id = sc.nextLine();

                        System.out.print("Full Name: ");
                        String name = sc.nextLine();

                        System.out.print("Age: ");
                        int age = Integer.parseInt(sc.nextLine());

                        System.out.print("Nationality: ");
                        String nationality = sc.nextLine();

                        System.out.print("Position: ");
                        String position = sc.nextLine();

                        Player p = new Player(
                                id,
                                name,
                                age,
                                nationality,
                                position
                        );

                        manager.addPlayer(p);

                        break;

                    case 2:

                        System.out.print("Enter Player ID: ");
                        String updateId = sc.nextLine();

                        System.out.print("New Position: ");
                        String newPosition = sc.nextLine();

                        manager.updatePlayer(
                                updateId,
                                newPosition
                        );

                        break;

                    case 3:

                        manager.displayPlayers();

                        break;

                    case 4:

                        System.out.print("Enter Name: ");
                        String keyword = sc.nextLine();

                        manager.searchPlayer(keyword);

                        break;

                    case 5:

                        System.out.println("Exit Program!");
                        return;

                    default:

                        System.out.println("Invalid Choice!");
                }
            }
        }
    }

    // =========================================================================
    // [FILE 2] PrjFootball.java
    // =========================================================================
    public static class Source_PrjFootball {
        
        // ===== MODEL CLASSES =====
        public static class Player {
            private String id;
            private String name;
            private int age;
            private String nationality;
            private String position;
            private int shirtNumber;
            private String type;   // Regular / Star
            private String status; // Active / Inactive

            public Player(String id, String name, int age, String nationality,
                          String position, int shirtNumber, String type, String status) {
                this.id = id;
                this.name = name;
                this.age = age;
                this.nationality = nationality;
                this.position = position;
                this.shirtNumber = shirtNumber;
                this.type = type;
                this.status = status;
            }

            public String getId() { return id; }
            public String getName() { return name; }
            public int getAge() { return age; }
            public String getNationality() { return nationality; }
            public String getPosition() { return position; }
            public int getShirtNumber() { return shirtNumber; }
            public String getType() { return type; }
            public String getStatus() { return status; }
        }

        // Training Session
        public static class TrainingSession {
            private String trainingId;
            private String date;
            private String location;
            private String topic;

            public TrainingSession(String trainingId, String date, String location, String topic) {
                this.trainingId = trainingId;
                this.date = date;
                this.location = location;
                this.topic = topic;
            }

            public String getTrainingId() { return trainingId; }
            public String getDate() { return date; }
            public String getLocation() { return location; }
            public String getTopic() { return topic; }
        }

        // Attendance Record
        public static class AttendanceRecord {
            private String trainingId;
            private Map<String, Boolean> attendance; // PlayerID -> true=present, false=absent

            public AttendanceRecord(String trainingId) {
                this.trainingId = trainingId;
                this.attendance = new HashMap<>();
            }

            public void markAttendance(List<Player> activePlayers, List<String> absentIds) {
                for (Player p : activePlayers) {
                    if (absentIds.contains(p.getId())) {
                        attendance.put(p.getId(), false);
                    } else {
                        attendance.put(p.getId(), true);
                    }
                }
            }

            public void printSummary() {
                long present = attendance.values().stream().filter(v -> v).count();
                long absent = attendance.size() - present;
                System.out.println("Summary:");
                System.out.println("Present: " + present);
                System.out.println("Absent: " + absent);
            }
        }

        // ===== MANAGER CLASSES =====
        public static class PlayerManager {
            private List<Player> players = new ArrayList<>();

            // Task S5 - View Player Details
            public void viewPlayerDetails(Scanner sc) {
                System.out.print("Enter Player ID: ");
                String id = sc.nextLine();
                for (Player p : players) {
                    if (p.getId().equalsIgnoreCase(id)) {
                        System.out.println("Player ID: " + p.getId());
                        System.out.println("Full Name: " + p.getName());
                        System.out.println("Age: " + p.getAge());
                        System.out.println("Nationality: " + p.getNationality());
                        System.out.println("Position: " + p.getPosition());
                        System.out.println("Shirt Number: " + p.getShirtNumber());
                        System.out.println("Player Type: " + p.getType());
                        System.out.println("Status: " + p.getStatus());
                        return;
                    }
                }
                System.out.println("Player not found!");
            }

            public List<Player> getActivePlayers() {
                List<Player> active = new ArrayList<>();
                for (Player p : players) {
                    if (p.getStatus().equalsIgnoreCase("Active")) active.add(p);
                }
                return active;
            }
        }

        public static class TrainingManager {
            private List<TrainingSession> sessions = new ArrayList<>();
            private Map<String, AttendanceRecord> attendanceRecords = new HashMap<>();

            // Task S6 - Create Training Session
            public void createTrainingSession(Scanner sc) {
                System.out.print("Training ID: ");
                String id = sc.nextLine();
                System.out.print("Date (dd/MM/yyyy): ");
                String date = sc.nextLine();
                System.out.print("Location: ");
                String location = sc.nextLine();
                System.out.print("Topic: ");
                String topic = sc.nextLine();

                TrainingSession ts = new TrainingSession(id, date, location, topic);
                sessions.add(ts);
                System.out.println("Training session created successfully.");
            }

            // Task S7 - Record Training Attendance
            public void recordAttendance(Scanner sc, PlayerManager pm) {
                System.out.print("Training ID: ");
                String id = sc.nextLine();
                TrainingSession ts = null;
                for (TrainingSession s : sessions) {
                    if (s.getTrainingId().equalsIgnoreCase(id)) {
                        ts = s;
                        break;
                    }
                }

                if (ts == null) {
                    System.out.println("Training not found!");
                    return;
                }

                List<Player> activePlayers = pm.getActivePlayers();
                System.out.println("Total Active Players: " + activePlayers.size());
                System.out.print("Enter absent Player IDs (comma separated): ");
                String input = sc.nextLine();
                List<String> absentIds = input.isEmpty() ? new ArrayList<>() : Arrays.asList(input.split(","));

                AttendanceRecord ar = new AttendanceRecord(id);
                ar.markAttendance(activePlayers, absentIds);
                attendanceRecords.put(id, ar);

                System.out.println("Training attendance was recorded successfully.");
                ar.printSummary();
            }

            // Task S9 - View Training History
            public void viewTrainingHistory() {
                System.out.println("TRAINING HISTORY");
                for (TrainingSession ts : sessions) {
                    System.out.printf("%s | %s | %s | %s\n",
                            ts.getTrainingId(), ts.getDate(), ts.getLocation(), ts.getTopic());
                }
            }
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            PlayerManager pm = new PlayerManager();
            TrainingManager tm = new TrainingManager();

            int choice;
            do {
                System.out.println("\n--- MENU ---");
                System.out.println("1. View Player Details ");
                System.out.println("2. Create Training Session ");
                System.out.println("3. Record Training Attendance ");
                System.out.println("4. View Training History (S9)");
                System.out.println("5. Exit");
                System.out.print("Choose: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        pm.viewPlayerDetails(sc);
                        break;
                    case 2:
                        tm.createTrainingSession(sc);
                        break;
                    case 3:
                        tm.recordAttendance(sc, pm);
                        break;
                    case 4:
                        tm.viewTrainingHistory();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 5);
        }
    }

    // =========================================================================
    // [FILE 3] Task8Oop.java
    // =========================================================================
    public static class Source_Task8Oop {

        private String matchID;
        private String date;
        private String opponentTeam;
        private String matchType;

        public Source_Task8Oop() {
        }

        public Source_Task8Oop(String matchID, String date,
                        String opponentTeam, String matchType) {
            this.matchID = matchID;
            this.date = date;
            this.opponentTeam = opponentTeam;
            this.matchType = matchType;
        }

        public String getMatchID() {
            return matchID;
        }

        public void setMatchID(String matchID) {
            this.matchID = matchID;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getOpponentTeam() {
            return opponentTeam;
        }

        public void setOpponentTeam(String opponentTeam) {
            this.opponentTeam = opponentTeam;
        }

        public String getMatchType() {
            return matchType;
        }

        public void setMatchType(String matchType) {
            this.matchType = matchType;
        }

        // Kiểm tra Match ID trùng
        public static boolean isDuplicateID(
                ArrayList<Source_Task8Oop> list,
                String id) {

            for (Source_Task8Oop match : list) {
                if (match.getMatchID().equalsIgnoreCase(id)) {
                    return true;
                }
            }
            return false;
        }

        // Kiểm tra ngày hợp lệ
        public static boolean isValidDate(String date) {

            try {

                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("dd/MM/uuuu")
                                .withResolverStyle(ResolverStyle.STRICT);

                LocalDate.parse(date, formatter);

                return true;

            } catch (Exception e) {
                return false;
            }
        }

        // Nhập dữ liệu
        public void input(ArrayList<Source_Task8Oop> matchList) {

            Scanner sc = new Scanner(System.in);

            // Match ID
            while (true) {

                System.out.print("Match ID: ");
                String id = sc.nextLine().trim();

                if (id.isEmpty()) {
                    System.out.println("Match ID cannot be empty!");
                    continue;
                }

                if (isDuplicateID(matchList, id)) {
                    System.out.println("Match ID already exists!");
                    continue;
                }

                this.matchID = id;
                break;
            }

            // Date
            while (true) {

                System.out.print("Date (dd/MM/yyyy): ");
                String d = sc.nextLine().trim();

                if (!isValidDate(d)) {
                    System.out.println("Invalid date!");
                    continue;
                }

                this.date = d;
                break;
            }

            // Opponent Team
            while (true) {

                System.out.print("Opponent Team: ");
                String team = sc.nextLine().trim();

                if (team.isEmpty()) {
                    System.out.println("Opponent Team cannot be empty!");
                    continue;
                }

                this.opponentTeam = team;
                break;
            }

            // Match Type
            while (true) {

                System.out.println("\nMatch Type:");
                System.out.println("1. Friendly");
                System.out.println("2. League");
                System.out.println("3. Cup");

                System.out.print("Choose Match Type: ");

                try {

                    int choice =
                            Integer.parseInt(sc.nextLine());

                    switch (choice) {

                        case 1:
                            this.matchType = "Friendly";
                            return;

                        case 2:
                            this.matchType = "League";
                            return;

                        case 3:
                            this.matchType = "Cup";
                            return;

                        default:
                            System.out.println("Invalid choice!");
                    }

                } catch (Exception e) {
                    System.out.println("Please enter a number!");
                }
            }
        }

        // Hiển thị
        public void display() {

            System.out.printf(
                    "%-8s %-15s %-20s %-15s\n",
                    matchID,
                    date,
                    opponentTeam,
                    matchType
            );
        }

        // Main test
        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);

            ArrayList<Source_Task8Oop> matchList =
                    new ArrayList<>();

            while (true) {

                System.out.println("\n=========== CREATE MATCH RECORD ===========");

                Source_Task8Oop match = new Source_Task8Oop();

                match.input(matchList);

                System.out.println("\n[1] Submit");
                System.out.println("[2] Cancel");

                System.out.print("Choose an option: ");

                int option =
                        Integer.parseInt(sc.nextLine());

                if (option == 1) {

                    matchList.add(match);

                    System.out.println(
                            "\nMatch record created successfully.");

                } else {

                    System.out.println(
                            "\nCreate match cancelled.");
                }

                System.out.print(
                        "\nDo you want to continue? (Y/N): ");

                String answer = sc.nextLine();

                if (answer.equalsIgnoreCase("N")) {
                    break;
                }
            }

            System.out.println("\n=========== MATCH HISTORY ===========");

            System.out.printf(
                    "%-8s %-15s %-20s %-15s\n",
                    "ID",
                    "Date",
                    "Opponent Team",
                    "Match Type"
            );

            System.out.println(
                    "------------------------------------------------------------");

            for (Source_Task8Oop match : matchList) {
                match.display();
            }
        }
    }

    // =========================================================================
    // [FILE 4] Task10Oop.java
    // =========================================================================
    public static class Source_Task10Oop {

        public void viewMatchHistory(ArrayList<Source_Task8Oop> matchList) {

            System.out.println("\n----------- MATCH HISTORY -----------");

            if (matchList == null || matchList.isEmpty()) {

                System.out.println("No match records found.");
                System.out.println("\nPress ENTER to return...");
                new Scanner(System.in).nextLine();
                return;
            }

            System.out.printf(
                    "%-8s %-15s %-20s %-15s\n",
                    "ID",
                    "Date",
                    "Opponent Team",
                    "Match Type");

            System.out.println(
                    "--------------------------------------------------------------");

            for (Source_Task8Oop match : matchList) {

                System.out.printf(
                        "%-8s %-15s %-20s %-15s\n",
                        match.getMatchID(),
                        match.getDate(),
                        match.getOpponentTeam(),
                        match.getMatchType());
            }

            System.out.println(
                    "--------------------------------------------------------------");

            System.out.println("\nPress ENTER to return...");
            new Scanner(System.in).nextLine();
        }

        // Test riêng Task 10
        public static void main(String[] args) {

            ArrayList<Source_Task8Oop> matchList =
                    new ArrayList<>();

            matchList.add(
                    new Source_Task8Oop(
                            "M01",
                            "20/12/2026",
                            "Hanoi Eagles",
                            "League"));

            matchList.add(
                    new Source_Task8Oop(
                            "M02",
                            "25/12/2026",
                            "Da Nang FC",
                            "Friendly"));

            matchList.add(
                    new Source_Task8Oop(
                            "M03",
                            "30/12/2026",
                            "Ho Chi Minh City FC",
                            "Cup"));

            Source_Task10Oop task10 =
                    new Source_Task10Oop();

            task10.viewMatchHistory(matchList);
        }
    }

    // =========================================================================
    // [FILE 5] Task11Oop.java
    // =========================================================================
    public static class Source_Task11Oop {

        private String matchID;
        private String playerID;
        private int goals;
        private int assists;
        private int yellowCards;
        private int redCards;
        private int minutesPlayed;

        public Source_Task11Oop() {
        }

        public Source_Task11Oop(String matchID, String playerID,
                int goals, int assists,
                int yellowCards, int redCards,
                int minutesPlayed) {

            this.matchID = matchID;
            this.playerID = playerID;
            this.goals = goals;
            this.assists = assists;
            this.yellowCards = yellowCards;
            this.redCards = redCards;
            this.minutesPlayed = minutesPlayed;
        }

        public String getMatchID() {
            return matchID;
        }

        public void setMatchID(String matchID) {
            this.matchID = matchID;
        }

        public String getPlayerID() {
            return playerID;
        }

        public void setPlayerID(String playerID) {
            this.playerID = playerID;
        }

        public int getGoals() {
            return goals;
        }

        public void setGoals(int goals) {
            this.goals = goals;
        }

        public int getAssists() {
            return assists;
        }

        public void setAssists(int assists) {
            this.assists = assists;
        }

        public int getYellowCards() {
            return yellowCards;
        }

        public void setYellowCards(int yellowCards) {
            this.yellowCards = yellowCards;
        }

        public int getRedCards() {
            return redCards;
        }

        public void setRedCards(int redCards) {
            this.redCards = redCards;
        }

        public int getMinutesPlayed() {
            return minutesPlayed;
        }

        public void setMinutesPlayed(int minutesPlayed) {
            this.minutesPlayed = minutesPlayed;
        }

        public void input() {

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("Goals: ");
                goals = Integer.parseInt(sc.nextLine());

                if (goals >= 0) {
                    break;
                }

                System.out.println("Goals cannot be negative!");
            }

            while (true) {
                System.out.print("Assists: ");
                assists = Integer.parseInt(sc.nextLine());

                if (assists >= 0) {
                    break;
                }

                System.out.println("Assists cannot be negative!");
            }

            while (true) {
                System.out.print("Yellow Cards: ");
                yellowCards = Integer.parseInt(sc.nextLine());

                if (yellowCards >= 0) {
                    break;
                }

                System.out.println("Yellow Cards cannot be negative!");
            }

            while (true) {
                System.out.print("Red Cards: ");
                redCards = Integer.parseInt(sc.nextLine());

                if (redCards >= 0) {
                    break;
                }

                System.out.println("Red Cards cannot be negative!");
            }

            while (true) {

                System.out.print("Minutes Played: ");
                minutesPlayed = Integer.parseInt(sc.nextLine());

                if (minutesPlayed >= 0 && minutesPlayed <= 120) {
                    break;
                }

                System.out.println(
                        "Minutes Played must be between 0 and 120!");
            }

            // BR21
            if (minutesPlayed == 0) {

                goals = 0;
                assists = 0;
                yellowCards = 0;
                redCards = 0;
            }
        }

        public int calculatePerformancePoints() {

            int points =
                    goals * 5
                    + assists * 3
                    - yellowCards
                    - redCards * 3;

            return Math.max(points, 0);
        }

        public void display() {

            System.out.printf(
                    "%-10s %-10s %-8d %-8d %-8d %-8d %-10d %-10d\n",
                    matchID,
                    playerID,
                    goals,
                    assists,
                    yellowCards,
                    redCards,
                    minutesPlayed,
                    calculatePerformancePoints());
        }

        public static int findPerformance(
                ArrayList<Source_Task11Oop> performanceList,
                String matchID,
                String playerID) {

            for (int i = 0; i < performanceList.size(); i++) {

                Source_Task11Oop p = performanceList.get(i);

                if (p.getMatchID().equalsIgnoreCase(matchID)
                        && p.getPlayerID().equalsIgnoreCase(playerID)) {

                    return i;
                }
            }

            return -1;
        }

        public static void displayAll(
                ArrayList<Source_Task11Oop> performanceList) {

            System.out.println(
                    "\n---------------- PERFORMANCE LIST ----------------");

            if (performanceList.isEmpty()) {

                System.out.println("No performance records found.");
                return;
            }

            System.out.printf(
                    "%-10s %-10s %-8s %-8s %-8s %-8s %-10s %-10s\n",
                    "MatchID",
                    "PlayerID",
                    "Goals",
                    "Assist",
                    "Yellow",
                    "Red",
                    "Minutes",
                    "Points");

            System.out.println(
                    "--------------------------------------------------------------------------");

            for (Source_Task11Oop p : performanceList) {
                p.display();
            }
        }

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);

            ArrayList<Source_Task11Oop> performanceList =
                    new ArrayList<>();

            int choice;

            do {

                System.out.println(
                        "\n===== TASK 11 - PLAYER PERFORMANCE =====");

                System.out.println("1. Add / Update Performance");
                System.out.println("2. View All Performance");
                System.out.println("0. Exit");

                System.out.print("Choose: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:

                        System.out.print("Match ID: ");
                        String matchID = sc.nextLine();

                        System.out.print("Player ID: ");
                        String playerID = sc.nextLine();

                        int index =
                                findPerformance(
                                        performanceList,
                                        matchID,
                                        playerID);

                        if (index != -1) {

                            System.out.println(
                                    "\nExisting Performance Found:");

                            performanceList.get(index).display();

                            System.out.println(
                                    "\n1. Replace");
                            System.out.println(
                                    "2. Cancel");

                            System.out.print(
                                    "Choose: ");

                            int replace =
                                    Integer.parseInt(sc.nextLine());

                            if (replace == 1) {

                                Source_Task11Oop performance =
                                        new Source_Task11Oop();

                                performance.setMatchID(matchID);
                                performance.setPlayerID(playerID);

                                performance.input();

                                performanceList.set(
                                        index,
                                        performance);

                                System.out.println(
                                        "Performance updated successfully.");

                                System.out.println(
                                        "Performance Points: "
                                        + performance.calculatePerformancePoints());
                            }

                        } else {

                            Source_Task11Oop performance =
                                    new Source_Task11Oop();

                            performance.setMatchID(matchID);
                            performance.setPlayerID(playerID);

                            performance.input();

                            performanceList.add(performance);

                            System.out.println(
                                    "Performance saved successfully.");

                            System.out.println(
                                    "Performance Points: "
                                    + performance.calculatePerformancePoints());
                        }

                        break;

                    case 2:

                        displayAll(performanceList);

                        break;

                    case 0:

                        System.out.println("Exit Program.");
                        break;

                    default:

                        System.out.println("Invalid choice!");
                }

            } while (choice != 0);
        }
    }

    // =========================================================================
    // [FILE 6] task12,13
    // =========================================================================
    public static class SalaryManager {

        public void calculateSalary() {

            System.out.println("----------- CALCULATE PLAYER SALARY -----------");
            String playerName = "Nguyen Van Minh";
            String playerType = "Star Player";

            double baseSalary = 28000000;

            int goals = 2;
            int assists = 1;
            int yellowCards = 0;
            int redCards = 0;
            int minutesPlayed = 90;
            int performancePoints =
                    (goals * 5)
                    + (assists * 3)
                    - yellowCards
                    - (redCards * 3)
                    + (minutesPlayed / 10);
            double bonus = performancePoints * 500000;
            double totalSalary = baseSalary + bonus;

            System.out.println("Player: " + playerName);
            System.out.println("Type: " + playerType);
            System.out.println("Performance Points: " + performancePoints);

            System.out.println("Base Salary: " + baseSalary + " VND");
            System.out.println("Performance Bonus: " + bonus + " VND");
            System.out.println("Total Salary: " + totalSalary + " VND");
        }

        public void salarySummaryReport() {

            double baseSalary = 28000000;
            double bonus = 11000000;
            double totalSalary = baseSalary + bonus;

            System.out.println("\n----------- SALARY SUMMARY REPORT -----------");
            System.out.printf("%-5s %-20s %-15s %-15.0f %-15.0f %-15.0f\n",
                    "P01",
                    "Nguyen Van Minh",
                    "Star Player",
                    baseSalary,
                    bonus,
                    totalSalary);

            System.out.println("----------------------------------------------");

            System.out.println("Total Monthly Salary Cost: "
                    + totalSalary + " VND");
        }
    }

    // =========================================================================
    // [FILE 7] Task14Oop.java
    // =========================================================================
    public static class Source_Task14Oop {

        private String playerID;
        private String playerName;
        private String position;
        private int totalGoals;

        public Source_Task14Oop() {
        }

        public Source_Task14Oop(String playerID,
                         String playerName,
                         String position,
                         int totalGoals) {

            this.playerID = playerID;
            this.playerName = playerName;
            this.position = position;
            this.totalGoals = totalGoals;
        }

        public String getPlayerID() {
            return playerID;
        }

        public void setPlayerID(String playerID) {
            this.playerID = playerID;
        }

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getTotalGoals() {
            return totalGoals;
        }

        public void setTotalGoals(int totalGoals) {
            this.totalGoals = totalGoals;
        }

        public void input() {

            Scanner sc = new Scanner(System.in);

            System.out.print("Player ID: ");
            playerID = sc.nextLine();

            System.out.print("Player Name: ");
            playerName = sc.nextLine();

            System.out.print("Position: ");
            position = sc.nextLine();

            while (true) {

                try {

                    System.out.print("Total Goals: ");
                    totalGoals =
                            Integer.parseInt(sc.nextLine());

                    if (totalGoals >= 0) {
                        break;
                    }

                    System.out.println(
                            "Goals cannot be negative!");

                } catch (Exception e) {

                    System.out.println(
                            "Please enter a valid number!");
                }
            }
        }

        public void display(int rank) {

            System.out.printf(
                    "%-6d %-12s %-25s %-15s %-8d\n",
                    rank,
                    playerID,
                    playerName,
                    position,
                    totalGoals);
        }

        public static void sortTopScorers(
                ArrayList<Source_Task14Oop> playerList) {

            Collections.sort(
                    playerList,
                    new Comparator<Source_Task14Oop>() {

                @Override
                public int compare(
                        Source_Task14Oop p1,
                        Source_Task14Oop p2) {

                    return p2.getTotalGoals()
                            - p1.getTotalGoals();
                }
            });
        }

        public static void displayTopScorers(
                ArrayList<Source_Task14Oop> playerList) {

            System.out.println(
                    "\n----------- ALL-TIME TOP GOAL SCORERS -----------");

            if (playerList.isEmpty()) {

                System.out.println(
                        "No data available.");
                return;
            }

            System.out.printf(
                    "%-6s %-12s %-25s %-15s %-8s\n",
                    "Rank",
                    "Player ID",
                    "Name",
                    "Position",
                    "Goals");

            System.out.println(
                    "--------------------------------------------------------------------------");

            int rank = 1;

            for (Source_Task14Oop player : playerList) {

                player.display(rank);

                rank++;
            }

            System.out.println(
                    "--------------------------------------------------------------------------");
        }

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);

            ArrayList<Source_Task14Oop> playerList =
                    new ArrayList<>();

            int n;

            System.out.print(
                    "Enter number of players: ");

            n = Integer.parseInt(sc.nextLine());

            for (int i = 0; i < n; i++) {

                System.out.println(
                        "\nPlayer " + (i + 1));

                Source_Task14Oop player =
                        new Source_Task14Oop();

                player.input();

                playerList.add(player);
            }

            sortTopScorers(playerList);

            displayTopScorers(playerList);

            System.out.println(
                    "\nPress ENTER to return...");
            sc.nextLine();
        }
    }

    // =========================================================================
    // [FILE 8] task15
    // =========================================================================
    public static class FileManager {

        public void exitProgram() {

            Scanner sc = new Scanner(System.in);
            System.out.println("\n----------- EXIT SYSTEM -----------");

            System.out.println("1. Save and Exit");
            System.out.println("2. Exit without Saving");
            System.out.println("3. Cancel");

            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Data saved successfully.");
                    System.out.println("Thank you for using the Football Player Management System.");
                    System.exit(0);
                    break;
                case 2:
                    System.exit(0);
                    break;
                case 3:
                    System.out.println("Cancelled.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // =========================================================================
    // MAIN MENU SYSTEM - REWRITTEN TO CLEAN ENGLISH TEXT
    // =========================================================================
    public static void main(String[] args) {
        Scanner mainSc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=============================================");
            System.out.println("        FOOTBALL MANAGEMENT SYSTEM MENU      ");
            System.out.println("=============================================");
            System.out.println("1. Player Management Profile (Add, Update, Search)");
            System.out.println("2. Training Management (Sessions, Attendance & Rosters)");
            System.out.println("3. Create Match Event Logs");
            System.out.println("4. View Historical Match Logs");
            System.out.println("5. Update Player Performance Metrics");
            System.out.println("6. Financial Management (Payroll Costs & Summary Records)");
            System.out.println("7. View Club Directory & All-Time Top Scorers Leaderboard");
            System.out.println("8. Shut Down Management Application Context");
            System.out.print("Choose system module to test (1-8): ");
            
            try {
                int runChoice = Integer.parseInt(mainSc.nextLine());
                switch (runChoice) {
                    case 1:
                        Source_cohuongdoituong.main(new String[0]);
                        break;
                    case 2:
                        Source_PrjFootball.main(new String[0]);
                        break;
                    case 3:
                        Source_Task8Oop.main(new String[0]);
                        break;
                    case 4:
                        Source_Task10Oop.main(new String[0]);
                        break;
                    case 5:
                        Source_Task11Oop.main(new String[0]);
                        break;
                    case 6:
                        SalaryManager sm = new SalaryManager();
                        sm.calculateSalary();
                        sm.salarySummaryReport();
                        break;
                    case 7:
                        Source_Task14Oop.main(new String[0]);
                        break;
                    case 8:
                        FileManager fm = new FileManager();
                        fm.exitProgram();
                        break;
                    default:
                        System.out.println("Out of bounds choice! Please select 1-8.");
                }
            } catch (Exception e) {
                System.out.println("Please input a valid choice number!");
            }
        }
    }
}