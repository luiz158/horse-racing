package com.intenthq.horseracing.controllers;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;

import com.intenthq.horseracing.NonExistingTrackException;
import com.intenthq.horseracing.model.Racer;

@Controller
@Scope(value="session")
public class Race {

    private List<TrackRacerPair> trackRacerPairList;
    private int trackLength=0; 
    private int numOfTracks=0; 
    private boolean raceFinished;
    
    public void initRace(int numOfTracks, int trackLength) {
        trackRacerPairList = new ArrayList<TrackRacerPair>();
        this.numOfTracks = numOfTracks;
        this.trackLength = trackLength;
        raceFinished = false;
    }

    public void addNewRacer(Racer racer, int track) throws NonExistingTrackException {
        if (trackRacerPairList == null) {
            throw new NonExistingTrackException();
        }
        if (trackRacerPairList.size() < numOfTracks) {
            trackRacerPairList.add(new TrackRacerPair(racer, track));
        } else {
            throw new NonExistingTrackException();
        }
    }
    
    public String getRaceState() throws NonExistingTrackException {
        if (trackRacerPairList == null) {
            throw new NonExistingTrackException();
        }
		StringBuilder sb = new StringBuilder();
		sb.append("Position, Lane, Horse name").append("\n");
		
        List<TrackRacerPair> orderedList = getOrderByPosition();
        
		for (int i=0;i<orderedList.size();++i) {
		   sb.append((i+1)).append(", ").append(orderedList.get(i).getTrack()+1).append(", ").append(orderedList.get(i).getRacer().getName());
           sb.append("\n");   	
		}

        if (raceFinished) {
            sb.append("\nThe race is finished!");
        }

        return sb.toString();
	}

    public void updateRace(String[] updates) throws NonExistingTrackException {
        if (raceFinished) {
            return;
        }

        for(String update : updates) {
           if (updateTrack(update)>=trackLength) { //WE HAVE A WINNER
                raceFinished = true;
                break;
           }
        }
    }
    
    public boolean hasRacers() {
        return !trackRacerPairList.isEmpty();
    }

    public int getNumOfTracks() {
        return numOfTracks;
    }

    private List<TrackRacerPair> getOrderByPosition() {

        List<TrackRacerPair> sortedList = new ArrayList<TrackRacerPair>(trackRacerPairList);
        Collections.sort(sortedList);
        return sortedList;
    }

    private int updateTrack(String update) throws NonExistingTrackException {
        try {
            String[] updateFields = update.split("\\s+");
            return (trackRacerPairList.get(Integer.parseInt(updateFields[0])-1)).addYards(Integer.parseInt(updateFields[1]));
        } catch (IndexOutOfBoundsException ioofbE) {
            throw new NonExistingTrackException();
        }
    }
    
    class TrackRacerPair implements Comparable<TrackRacerPair> {
        private Racer racer;
        private int track;
        private int yardsDone = 0;
        
        public TrackRacerPair(Racer racer, int track) {
            this.racer = racer;
            this.track = track;
        }    
        
        public Racer getRacer() {
            return racer;
        }

        public int getTrack() {
            return track;
        }
        
        public int addYards(int yardsToAdd) {
            yardsDone+=yardsToAdd;
            return yardsDone;
        }
        
        public int getYardsDone() {
            return this.yardsDone;
        }

        @Override
        public int compareTo(TrackRacerPair other){ 
            return ((Integer)other.getYardsDone()).compareTo((Integer)this.getYardsDone());
        }
    }
}
