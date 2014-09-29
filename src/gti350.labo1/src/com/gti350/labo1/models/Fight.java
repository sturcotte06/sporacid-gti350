package com.gti350.labo1.models;

import java.util.ArrayList;
import java.util.List;

import com.gti350.labo1.models.utils.ParcelableHelper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @author Simon Turcotte-Langevin
 * 
 */
public class Fight implements Parcelable {
	/** */
	private static final int MaxRound = 12;

	/** Creator object for the parcelable implementation. */
	public static final Parcelable.Creator<Fight> CREATOR = ParcelableHelper.getDefaultCreator(Fight.class);

	/** */
	private final List<Round> rounds;
	/** */
	private final Judge judge1;
	/** */
	private final Judge judge2;
	/** */
	private final Judge judge3;
	/** */
	private final Fighter fighter1;
	/** */
	private final Fighter fighter2;

	/**
	 * 
	 * @param fighter1
	 * @param fighter2
	 * @param judge1
	 * @param judge2
	 * @param judge3
	 */
	public Fight(Fighter fighter1, Fighter fighter2, Judge judge1, Judge judge2, Judge judge3) {
		if (fighter1 == null) {
			throw new IllegalArgumentException("fighter1 cannot be null.");
		}

		if (fighter2 == null) {
			throw new IllegalArgumentException("fighter2 cannot be null.");
		}

		if (fighter1.getColor() == fighter2.getColor()) {
			throw new IllegalArgumentException("fighter1 and fighter2 cannot have the same color.");
		}

		if (judge1 == null) {
			throw new IllegalArgumentException("judge1 cannot be null.");
		}

		if (judge2 == null) {
			throw new IllegalArgumentException("judge2 cannot be null.");
		}

		if (judge3 == null) {
			throw new IllegalArgumentException("judge3 cannot be null.");
		}

		this.rounds = new ArrayList<>(MaxRound);
		this.judge1 = judge1;
		this.judge2 = judge2;
		this.judge3 = judge3;
		this.fighter1 = fighter1;
		this.fighter2 = fighter2;
	}

	/**
	 * Parcelable required constructor.
	 * 
	 * @param src
	 *            The parcel that describes this judge.
	 */
	public Fight(Parcel src) {
		if (src == null) {
			throw new IllegalArgumentException("src cannot be null.");
		}

		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		this.fighter1 = src.readParcelable(classLoader);
		this.fighter2 = src.readParcelable(classLoader);
		this.judge1 = src.readParcelable(classLoader);
		this.judge2 = src.readParcelable(classLoader);
		this.judge3 = src.readParcelable(classLoader);

		this.rounds = new ArrayList<>();
		src.readTypedList(this.rounds, Round.CREATOR);
	}

	/**
	 * 
	 * @param judgeScore1
	 * @param judgeScore2
	 * @param judgeScore3
	 */
	public void registerRound(JudgeScore judgeScore1, JudgeScore judgeScore2, JudgeScore judgeScore3) {
		if (this.rounds.size() >= MaxRound) {
			throw new IllegalStateException("Cannot register a new round for this fight.");
		}

		this.rounds.add(new Round(judgeScore1, judgeScore2, judgeScore3));
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(fighter1, 0);
		dest.writeParcelable(fighter2, 0);
		dest.writeParcelable(judge1, 0);
		dest.writeParcelable(judge2, 0);
		dest.writeParcelable(judge3, 0);
		dest.writeTypedList(rounds);
	}

	/**
	 * @return the judge1
	 */
	public Judge getJudge1() {
		return judge1;
	}

	/**
	 * @return the judge2
	 */
	public Judge getJudge2() {
		return judge2;
	}

	/**
	 * @return the judge3
	 */
	public Judge getJudge3() {
		return judge3;
	}

	/**
	 * @return the fighter1
	 */
	public Fighter getFighter1() {
		return fighter1;
	}

	/**
	 * @return the fighter2
	 */
	public Fighter getFighter2() {
		return fighter2;
	}

	/**
	 * @return the rounds
	 */
	public List<Round> getRounds() {
		return rounds;
	}
}
