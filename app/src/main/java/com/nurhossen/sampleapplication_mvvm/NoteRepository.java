package com.nurhossen.sampleapplication_mvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;

    private LiveData<List<Note>> allnotes;

    public NoteRepository(Application application){

        NoteDatabase database = NoteDatabase.getInstance(application);

        noteDao= database.noteDao();
    }

    public void insert(Note note){

        new InsertNoteAsyncTask(noteDao).execute(note);
    }
    public void update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public void Deleteallnote(){

        new DeleteallNoteAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllnotes(){
        return allnotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }


    // update
    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.Update(notes[0]);
            return null;
        }
    }

    //delete
    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.Delete(notes[0]);
            return null;
        }
    }

    //deleteall
    private static class DeleteallNoteAsyncTask extends AsyncTask<Void,Void,Void>{

        private NoteDao noteDao;

        private DeleteallNoteAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.DeleteAll();
            return null;
        }
    }
}
