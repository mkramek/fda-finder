import { InjectionKey } from "vue";
import { createStore, Store, useStore } from "vuex";

export interface State {
    dark: boolean
}

export const key: InjectionKey<Store<State>> = Symbol();

export const store = createStore<State>({
    state: {
        dark: false
    },
    mutations: {
        set: (state: State, isDark: boolean) => {
            state.dark = isDark;
        },
        init: (state) => {
            if (localStorage.getItem('is_theme_dark')) {
                state.dark = JSON.parse(localStorage.is_theme_dark)
            } else {
                localStorage.setItem('is_theme_dark', JSON.stringify(false));
            }
        }
    }
});

export function useThemeStore() {
    return useStore(key);
}