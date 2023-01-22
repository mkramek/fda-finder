<template>
  <button @click="toggleTheme" class="flex items-center justify-center">
    <span v-if="theme.state.dark" class="material-icons md-36 rounded-full text-white">light_mode</span>
    <span v-else class="material-icons md-36 rounded-full">dark_mode</span>
  </button>
</template>

<script setup lang="ts">
import { useThemeStore } from "../store";

const theme = useThemeStore();

const toggleTheme = (ev: Event) => {
  ev.preventDefault();
  const newState = !theme.state.dark;
  theme.commit("set", newState);
}
</script>
