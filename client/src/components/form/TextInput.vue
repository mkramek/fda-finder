<template>
  <div class="flex flex-col items-stretch justify-center">
    <label class="text-lg">{{ label }}</label>
    <input :placeholder="required ? strings.input.required : strings.input.optional" @input="handleInput" :required="required" type="text" class="w-[80vw] md:w-[33vw] border px-3 py-2 text-lg rounded-md border-neutral-300 dark:bg-neutral-600" />
  </div>
</template>

<script setup lang="ts">
import strings from "../../assets/strings.json";

defineProps({
  modelValue: String,
  label: String,
  required: {
    type: Boolean,
    default: false
  }
});

const emit = defineEmits(['update:modelValue']);
const handleInput = (ev: Event) => {
  emit('update:modelValue', (ev.target as HTMLInputElement).value);
}
</script>
