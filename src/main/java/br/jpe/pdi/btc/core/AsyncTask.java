/*
 * Copyright (C) 2018 BotanicChallenge
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.jpe.pdi.btc.core;

/**
 * Async Task
 *
 * @param <B>
 * @param <T>
 */
public abstract class AsyncTask<T extends AsyncTask, B> implements Runnable {

    protected SucessCallback onSucessCallback;
    protected ErrorCallback onErrorCallback;
    protected FinallyCallback onFinallyCallback;

    @Override
    public final void run() {
        try {
            B result = runTask();
            if (onSucessCallback != null) {
                onSucessCallback.run(result);
            }
        } catch (Exception e) {
            if (onErrorCallback != null) {
                onErrorCallback.run(e);
            }
        } finally {
            if (onFinallyCallback != null) {
                onFinallyCallback.run();
            }
        }
    }

    protected abstract B runTask() throws Exception;

    public final T onSucessCallback(SucessCallback onSucessCallback) {
        this.onSucessCallback = onSucessCallback;
        return (T) this;
    }

    public final T onErrorCallback(ErrorCallback callback) {
        this.onErrorCallback = callback;
        return (T) this;
    }

    public final T onFinallyCallback(FinallyCallback callback) {
        this.onFinallyCallback = callback;
        return (T) this;
    }

}
