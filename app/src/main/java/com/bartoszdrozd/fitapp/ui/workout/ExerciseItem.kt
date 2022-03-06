package com.bartoszdrozd.fitapp.ui.workout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bartoszdrozd.fitapp.R
import com.bartoszdrozd.fitapp.model.workout.Exercise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseItem(exercise: Exercise, actions: WorkoutActions) {
    val smallPadding = dimensionResource(R.dimen.small_padding)
    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .padding(vertical = smallPadding)
    ) {
        Column(
            Modifier
                .padding(smallPadding)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(text = "Bench ${exercise.id}", Modifier.padding(smallPadding))
            }

            Column {
                exercise.sets.forEach { set ->
                    WorkoutSetItem(
                        set,
                        updateSet = { updatedSet ->
                            actions.updateSet(
                                updatedSet,
                                exercise.id
                            )
                        },
                        deleteSet = {
                            actions.deleteSet(it, exercise.id)
                        }
                    )
                }
                Surface(
                    Modifier.fillMaxWidth()
                ) {
                    Row(
                        Modifier
                            .wrapContentWidth()
                            .border(
                                BorderStroke(1.dp, LocalContentColor.current),
                                RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = smallPadding),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(
                            onClick = { actions.addSet(exercise) }
                        ) {
                            Icon(
                                Icons.Outlined.AddCircleOutline,
                                contentDescription = stringResource(R.string.add_set)
                            )
                        }

                        IconButton(
                            onClick = { actions.deleteExercise(exercise) }
                        ) {
                            Icon(
                                Icons.Outlined.DeleteOutline,
                                contentDescription = stringResource(R.string.delete_exercise)
                            )
                        }
                    }
                }
            }
        }
    }
}