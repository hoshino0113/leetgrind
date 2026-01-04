/* This is a correct but inefficient solution O(n**2), please see clean version for efficient solution.
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
return the minimum number of conference rooms required.

Intuition: Sort the array based on start time,
We keep a list of rooms that are required, for each meeting in the intervals, we check every room to see if they
are available ( if end time for last meeting in each room is <= start time of the new meeting)
If so, add the meeting to that room
if not, start a new room and add the meeting into that room
*/

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        //base case:
        if (intervals == null) return 0;

        if (intervals.length == 1) return 1;

        // we first initialize the room number
        int roomNum = 1;
        //sort the array based on the start time:
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        //initialize the room list:
        List<List<List<Integer>>> rooms = new ArrayList<>();
        //add 1 room to it:
        List<List<Integer>> initialRoom = new ArrayList<>();
        List<Integer> initialMeeting = new ArrayList<>();
        initialMeeting.add(intervals[0][0]);
        initialMeeting.add(intervals[0][1]);
        initialRoom.add(initialMeeting);
        rooms.add(initialRoom);

        for (int i = 1; i < intervals.length; i++) {
            //compare with the last scheduled meeting in each room
            //if there is a fit, add to that room
            //if there isn't, add a new room and increment the room num by 1
            boolean addRoom = false;
            for (List<List<Integer>> room : rooms) {
                if (room.get(room.size() - 1).get(1) <= intervals[i][0]) {
                    List<Integer> meeting = new ArrayList<>();
                    meeting.add(intervals[i][0]);
                    meeting.add(intervals[i][1]);
                    room.add(meeting);
                    addRoom = false;
                    break;
                } else {
                    addRoom = true;
                }
            }

            if (addRoom) {
                List<List<Integer>> newRoom = new ArrayList<>();
                rooms.add(newRoom);
                List<Integer> meeting = new ArrayList<>();
                meeting.add(intervals[i][0]);
                meeting.add(intervals[i][1]);
                rooms.get(rooms.size() - 1).add(meeting);
                roomNum++;
            }
        }

        return roomNum;
    }
}