<template>
  <div class="row">
    <div class="col-6" style="height:700px; border-right: 1px solid rgba(121, 121, 121, 0.692);">
      <Topic :isbn="this.$route.params.bookIsbn" />
    </div>

    <div class="col-6">
      <div v-if="anotherIsbn">
        <Topic :isbn="anotherIsbn" />
        <div @click="SelectAnotherBook">닫기</div>
      </div>
      <div v-else>
        <Select :isbn="this.$route.params.bookIsbn" @setIsbn="CompareTopic" />
      </div>
    </div>
  </div>
</template>

<script>
  import Topic from './Topics.vue'
  import Select from './Select.vue'

  export default {
    components: {
      Topic,
      Select,
    },
    data () {
      return {
        anotherIsbn: null,
      }
    },
    created() {
    },
    methods: {
      CompareTopic(isbn) {
        this.anotherIsbn = String(isbn)
      },
      SelectAnotherBook() {
        this.anotherIsbn = null
      }
    }
  }
</script>