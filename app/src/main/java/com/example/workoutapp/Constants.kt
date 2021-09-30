package com.example.workoutapp

class Constants {
    companion object {
        fun defaultExerciseList(): ArrayList<ExerciseModel> {

            val jumpingJacks = ExerciseModel(
                1,
                "Jumping Jacks",
                R.drawable.ic_jumping_jacks,
                false,
                false
            )

            val abdominalCrunch = ExerciseModel(
                2,
                "Abdominal Crunch",
                R.drawable.ic_abdominal_crunch,
                false,
                false
            )

            val highKnees = ExerciseModel(
                3,
                "High knees running in place",
                R.drawable.ic_high_knees_running_in_place,
                false,
                false
            )

            val lunge = ExerciseModel(
                4,
                "Lunge",
                R.drawable.ic_lunge,
                false,
                false
            )

            val plank = ExerciseModel(
                5,
                "Plank",
                R.drawable.ic_plank,
                false,
                false
            )

            val pushup = ExerciseModel(
                6,
                "Push up",
                R.drawable.ic_push_up,
                false,
                false
            )

            val pushupRotation = ExerciseModel(
                7,
                "Push up & rotation",
                R.drawable.ic_push_up_and_rotation,
                false,
                false
            )

            val sidePlank = ExerciseModel(
                8,
                "Side plank",
                R.drawable.ic_side_plank,
                false,
                false
            )

            val squat = ExerciseModel(
                9,
                "Squat",
                R.drawable.ic_squat,
                false,
                false
            )

            val stepUp = ExerciseModel(
                10,
                "Step up onto chair",
                R.drawable.ic_step_up_onto_chair,
                false,
                false
            )

            val triceps = ExerciseModel(
                11,
                "Triceps dip on chair",
                R.drawable.ic_triceps_dip_on_chair,
                false,
                false
            )

            val wallSit = ExerciseModel(
                12,
                "Wall sit",
                R.drawable.ic_wall_sit,
                false,
                false
            )

            return arrayListOf(
                jumpingJacks,
                abdominalCrunch,
                highKnees,
                lunge,
                plank,
                pushup,
                pushupRotation,
                sidePlank,
                squat,
                stepUp,
                triceps,
                wallSit
            )
        }
    }
}